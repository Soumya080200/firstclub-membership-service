package com.firstclub.membership.dto;

import com.firstclub.membership.enums.PlanType;
import com.firstclub.membership.enums.SubscriptionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record MembershipResponse(
        String userId,
        Long planId,
        PlanType planType,
        BigDecimal planBasePrice,
        int planDurationDays,
        Long tierId,
        String tierName,
        int tierRank,
        BigDecimal tierPriceMultiplier,
        List<BenefitResponse> benefits,
        BigDecimal effectivePrice,
        SubscriptionStatus status,
        LocalDateTime startedAt,
        LocalDateTime expiresAt
) {
}
