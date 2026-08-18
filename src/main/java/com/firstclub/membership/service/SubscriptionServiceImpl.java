package com.firstclub.membership.service;

import com.firstclub.membership.dto.ChangeTierRequest;
import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.exception.ConflictException;
import com.firstclub.membership.exception.NotEligibleException;
import com.firstclub.membership.exception.NotFoundException;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipTier;
import com.firstclub.membership.model.Subscription;
import com.firstclub.membership.enums.SubscriptionStatus;
import com.firstclub.membership.repository.SubscriptionRepository;
import com.firstclub.membership.service.tier.TierEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final MembershipCatalogService membershipCatalogService;
    private final TierEvaluationService tierEvaluationService;

    @Override
    public Subscription subscribe(SubscribeRequest request) {
        MembershipPlan plan = membershipCatalogService.getPlanOrThrow(request.planId());
        MembershipTier tier = membershipCatalogService.getTierOrThrow(request.tierId());

        if (subscriptionRepository.findActiveByUserId(request.userId()).isPresent()) {
            throw new ConflictException("User " + request.userId() + " already has an active subscription");
        }
        if (!tierEvaluationService.isEligible(tier, request.userId())) {
            throw new NotEligibleException("User " + request.userId() + " does not meet the criteria for tier " + tier.getName());
        }

        LocalDateTime now = LocalDateTime.now();
        Subscription subscription = Subscription.builder()
                .userId(request.userId())
                .planId(plan.getId())
                .tierId(tier.getId())
                .status(SubscriptionStatus.ACTIVE)
                .startedAt(now)
                .expiresAt(now.plusDays(plan.getDurationDays()))
                .build();
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription updateTier(String userId, ChangeTierRequest request) {
        Subscription subscription = getActiveOrThrow(userId);
        MembershipTier currentTier = membershipCatalogService.getTierOrThrow(subscription.getTierId());
        MembershipTier targetTier = membershipCatalogService.getTierOrThrow(request.targetTierId());

        if (targetTier.getRank() > currentTier.getRank() && !tierEvaluationService.isEligible(targetTier, userId)) {
            throw new NotEligibleException("User " + userId + " does not meet the criteria for tier " + targetTier.getName());
        }

        subscription.setTierId(targetTier.getId());
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription cancel(String userId) {
        Subscription subscription = getActiveOrThrow(userId);
        subscription.setStatus(SubscriptionStatus.CANCELLED);
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getCurrentByUserId(String userId) {
        return getActiveOrThrow(userId);
    }

    private Subscription getActiveOrThrow(String userId) {
        Subscription subscription = subscriptionRepository.findActiveByUserId(userId)
                .orElseThrow(() -> new NotFoundException("No active membership for user " + userId));

        if (subscription.getExpiresAt().isBefore(LocalDateTime.now())) {
            subscription.setStatus(SubscriptionStatus.EXPIRED);
            subscriptionRepository.save(subscription);
            throw new NotFoundException("No active membership for user " + userId);
        }
        return subscription;
    }
}
