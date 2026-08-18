package com.firstclub.membership.repository;

import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.enums.PlanType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PlanRepository extends InMemoryRepository<MembershipPlan> {

    public PlanRepository() {
        save(MembershipPlan.builder().type(PlanType.MONTHLY).basePrice(new BigDecimal("199.00")).durationDays(30).build());
        save(MembershipPlan.builder().type(PlanType.QUARTERLY).basePrice(new BigDecimal("499.00")).durationDays(90).build());
        save(MembershipPlan.builder().type(PlanType.YEARLY).basePrice(new BigDecimal("1499.00")).durationDays(365).build());
    }
}
