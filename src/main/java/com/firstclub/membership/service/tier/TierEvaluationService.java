package com.firstclub.membership.service.tier;

import com.firstclub.membership.model.MembershipTier;

import java.util.List;

public interface TierEvaluationService {

    boolean isEligible(MembershipTier tier, String userId);

    List<MembershipTier> eligibleTiers(List<MembershipTier> allTiers, String userId);
}
