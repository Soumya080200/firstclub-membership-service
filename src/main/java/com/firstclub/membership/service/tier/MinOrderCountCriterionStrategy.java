package com.firstclub.membership.service.tier;

import com.firstclub.membership.constant.Constants.CriterionConfigKey;
import com.firstclub.membership.model.CriterionDefinition;
import com.firstclub.membership.model.UserActivity;
import com.firstclub.membership.enums.CriterionType;
import org.springframework.stereotype.Component;

@Component
public class MinOrderCountCriterionStrategy implements TierCriterionStrategy {

    @Override
    public CriterionType getType() {
        return CriterionType.MIN_ORDER_COUNT;
    }

    @Override
    public boolean isSatisfied(CriterionDefinition criterion, UserActivity activity) {
        int minOrders = ((Number) criterion.getConfig().get(CriterionConfigKey.MIN_ORDERS)).intValue();
        return activity.getTotalOrders() >= minOrders;
    }
}
