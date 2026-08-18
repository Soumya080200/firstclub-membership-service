package com.firstclub.membership.service.tier;

import com.firstclub.membership.constant.Constants.CriterionConfigKey;
import com.firstclub.membership.model.CriterionDefinition;
import com.firstclub.membership.model.UserActivity;
import com.firstclub.membership.enums.CriterionType;
import org.springframework.stereotype.Component;

@Component
public class CohortMembershipCriterionStrategy implements TierCriterionStrategy {

    @Override
    public CriterionType getType() {
        return CriterionType.COHORT_MEMBERSHIP;
    }

    @Override
    public boolean isSatisfied(CriterionDefinition criterion, UserActivity activity) {
        String cohort = (String) criterion.getConfig().get(CriterionConfigKey.COHORT);
        return activity.getCohorts().contains(cohort);
    }
}
