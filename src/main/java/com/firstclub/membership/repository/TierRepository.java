package com.firstclub.membership.repository;

import com.firstclub.membership.constant.Constants.BenefitConfigKey;
import com.firstclub.membership.constant.Constants.CriterionConfigKey;
import com.firstclub.membership.model.Benefit;
import com.firstclub.membership.model.CriterionDefinition;
import com.firstclub.membership.model.MembershipTier;
import com.firstclub.membership.enums.BenefitType;
import com.firstclub.membership.enums.CriterionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class TierRepository extends InMemoryRepository<MembershipTier> {

    public TierRepository() {
        save(MembershipTier.builder()
                .name("SILVER").rank(1).priceMultiplier(new BigDecimal("1.00"))
                .benefits(List.of(Benefit.builder().type(BenefitType.FREE_DELIVERY).config(Map.of(BenefitConfigKey.MIN_ORDER_VALUE, 499)).build()))
                .criteria(List.of())
                .build());

        save(MembershipTier.builder()
                .name("GOLD").rank(2).priceMultiplier(new BigDecimal("1.50"))
                .benefits(List.of(
                        Benefit.builder().type(BenefitType.FREE_DELIVERY).config(Map.of(BenefitConfigKey.MIN_ORDER_VALUE, 199)).build(),
                        Benefit.builder().type(BenefitType.PERCENT_DISCOUNT).config(Map.of(BenefitConfigKey.PERCENT, 5, BenefitConfigKey.CATEGORIES, List.of("grocery"))).build(),
                        Benefit.builder().type(BenefitType.EARLY_ACCESS).config(Map.of(BenefitConfigKey.HOURS_BEFORE, 12)).build()
                ))
                .criteria(List.of(CriterionDefinition.builder().type(CriterionType.MIN_ORDER_COUNT).config(Map.of(CriterionConfigKey.MIN_ORDERS, 5)).build()))
                .build());

        save(MembershipTier.builder()
                .name("PLATINUM").rank(3).priceMultiplier(new BigDecimal("2.00"))
                .benefits(List.of(
                        Benefit.builder().type(BenefitType.FREE_DELIVERY).config(Map.of(BenefitConfigKey.MIN_ORDER_VALUE, 0)).build(),
                        Benefit.builder().type(BenefitType.PERCENT_DISCOUNT).config(Map.of(BenefitConfigKey.PERCENT, 10, BenefitConfigKey.CATEGORIES, List.of("grocery", "electronics"))).build(),
                        Benefit.builder().type(BenefitType.EXCLUSIVE_DEALS).config(Map.of()).build(),
                        Benefit.builder().type(BenefitType.PRIORITY_SUPPORT).config(Map.of(BenefitConfigKey.SLA_MINUTES, 15)).build()
                ))
                .criteria(List.of(
                        CriterionDefinition.builder().type(CriterionType.MIN_ORDER_COUNT).config(Map.of(CriterionConfigKey.MIN_ORDERS, 15)).build(),
                        CriterionDefinition.builder().type(CriterionType.MIN_MONTHLY_ORDER_VALUE).config(Map.of(CriterionConfigKey.MIN_MONTHLY_VALUE, 5000)).build()
                ))
                .build());
    }
}
