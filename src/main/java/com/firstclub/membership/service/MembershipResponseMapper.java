package com.firstclub.membership.service;

import com.firstclub.membership.dto.BenefitResponse;
import com.firstclub.membership.dto.MembershipResponse;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipTier;
import com.firstclub.membership.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MembershipResponseMapper {

    private final MembershipCatalogService membershipCatalogService;

    public MembershipResponse toResponse(Subscription subscription) {
        MembershipPlan plan = membershipCatalogService.getPlanOrThrow(subscription.getPlanId());
        MembershipTier tier = membershipCatalogService.getTierOrThrow(subscription.getTierId());

        return new MembershipResponse(
                subscription.getUserId(),
                plan.getId(),
                plan.getType(),
                plan.getBasePrice(),
                plan.getDurationDays(),
                tier.getId(),
                tier.getName(),
                tier.getRank(),
                tier.getPriceMultiplier(),
                tier.getBenefits().stream().map(b -> new BenefitResponse(b.getType(), b.getConfig())).toList(),
                membershipCatalogService.computeEffectivePrice(plan, tier),
                subscription.getStatus(),
                subscription.getStartedAt(),
                subscription.getExpiresAt()
        );
    }
}
