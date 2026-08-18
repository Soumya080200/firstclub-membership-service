package com.firstclub.membership.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserActivity {

    private String userId;
    private int totalOrders;
    private BigDecimal currentMonthOrderValue;
    private Set<String> cohorts;

    public static UserActivity empty(String userId) {
        return UserActivity.builder()
                .userId(userId)
                .totalOrders(0)
                .currentMonthOrderValue(BigDecimal.ZERO)
                .cohorts(Set.of())
                .build();
    }
}
