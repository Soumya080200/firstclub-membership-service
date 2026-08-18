package com.firstclub.membership.service.tier;

import com.firstclub.membership.constant.Constants.CriterionConfigKey;
import com.firstclub.membership.model.CriterionDefinition;
import com.firstclub.membership.model.UserActivity;
import com.firstclub.membership.enums.CriterionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MinMonthlyOrderValueCriterionStrategy implements TierCriterionStrategy {

    @Override
    public CriterionType getType() {
        return CriterionType.MIN_MONTHLY_ORDER_VALUE;
    }

    @Override
    public boolean isSatisfied(CriterionDefinition criterion, UserActivity activity) {
        BigDecimal minMonthlyValue = new BigDecimal(criterion.getConfig().get(CriterionConfigKey.MIN_MONTHLY_VALUE).toString());
        return activity.getCurrentMonthOrderValue().compareTo(minMonthlyValue) >= 0;
    }
}
