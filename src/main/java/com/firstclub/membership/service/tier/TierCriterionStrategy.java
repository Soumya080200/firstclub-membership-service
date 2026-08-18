package com.firstclub.membership.service.tier;

import com.firstclub.membership.model.CriterionDefinition;
import com.firstclub.membership.model.UserActivity;
import com.firstclub.membership.enums.CriterionType;

public interface TierCriterionStrategy {

    CriterionType getType();

    boolean isSatisfied(CriterionDefinition criterion, UserActivity activity);
}
