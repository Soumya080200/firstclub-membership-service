package com.firstclub.membership.model;

import com.firstclub.membership.enums.BenefitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Benefit {

    private BenefitType type;
    private Map<String, Object> config;
}
