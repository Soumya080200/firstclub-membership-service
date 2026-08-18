package com.firstclub.membership.dto;

import com.firstclub.membership.enums.BenefitType;

import java.util.Map;

public record BenefitResponse(
        BenefitType type,
        Map<String, Object> config
) {
}
