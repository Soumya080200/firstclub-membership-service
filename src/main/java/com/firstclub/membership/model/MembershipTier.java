package com.firstclub.membership.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipTier implements Identifiable {

    private Long id;
    private String name;
    private int rank;
    private BigDecimal priceMultiplier;
    private List<Benefit> benefits;
    private List<CriterionDefinition> criteria;
}
