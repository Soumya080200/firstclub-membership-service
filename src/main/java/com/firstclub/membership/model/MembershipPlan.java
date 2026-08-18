package com.firstclub.membership.model;

import com.firstclub.membership.enums.PlanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipPlan implements Identifiable {

    private Long id;
    private PlanType type;
    private BigDecimal basePrice;
    private int durationDays;
}
