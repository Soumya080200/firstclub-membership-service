package com.firstclub.membership.service;

import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipTier;

import java.math.BigDecimal;
import java.util.List;

public interface MembershipCatalogService {

    List<MembershipPlan> getAllPlans();

    List<MembershipTier> getAllTiers();

    MembershipPlan getPlanOrThrow(Long planId);

    MembershipTier getTierOrThrow(Long tierId);

    List<MembershipTier> getEligibleTiersByUserId(String userId);

    BigDecimal computeEffectivePrice(MembershipPlan plan, MembershipTier tier);
}
