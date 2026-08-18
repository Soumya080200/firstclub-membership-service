package com.firstclub.membership.service;

import com.firstclub.membership.exception.NotFoundException;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipTier;
import com.firstclub.membership.repository.PlanRepository;
import com.firstclub.membership.repository.TierRepository;
import com.firstclub.membership.service.tier.TierEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipCatalogServiceImpl implements MembershipCatalogService {

    private final PlanRepository planRepository;
    private final TierRepository tierRepository;
    private final TierEvaluationService tierEvaluationService;

    @Override
    public List<MembershipPlan> getAllPlans() {
        return planRepository.findAll();
    }

    @Override
    public List<MembershipTier> getAllTiers() {
        return tierRepository.findAll();
    }

    @Override
    public MembershipPlan getPlanOrThrow(Long planId) {
        return planRepository.findById(planId)
                .orElseThrow(() -> new NotFoundException("No plan found with id " + planId));
    }

    @Override
    public MembershipTier getTierOrThrow(Long tierId) {
        return tierRepository.findById(tierId)
                .orElseThrow(() -> new NotFoundException("No tier found with id " + tierId));
    }

    @Override
    public List<MembershipTier> getEligibleTiersByUserId(String userId) {
        return tierEvaluationService.eligibleTiers(tierRepository.findAll(), userId);
    }

    @Override
    public BigDecimal computeEffectivePrice(MembershipPlan plan, MembershipTier tier) {
        return plan.getBasePrice().multiply(tier.getPriceMultiplier()).setScale(2, RoundingMode.HALF_UP);
    }
}
