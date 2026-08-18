package com.firstclub.membership.repository;

import com.firstclub.membership.model.UserActivity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class UserActivityRepository {

    private final Map<String, UserActivity> store = new HashMap<>();

    public UserActivityRepository() {
        save(UserActivity.builder().userId("u-new").totalOrders(0).currentMonthOrderValue(BigDecimal.ZERO).cohorts(Set.of()).build());
        save(UserActivity.builder().userId("u-gold").totalOrders(8).currentMonthOrderValue(new BigDecimal("2000")).cohorts(Set.of()).build());
        save(UserActivity.builder().userId("u-plat").totalOrders(20).currentMonthOrderValue(new BigDecimal("8000")).cohorts(Set.of("EARLY_ADOPTER")).build());
    }

    public synchronized UserActivity save(UserActivity activity) {
        store.put(activity.getUserId(), activity);
        return activity;
    }

    public synchronized Optional<UserActivity> findByUserId(String userId) {
        return Optional.ofNullable(store.get(userId));
    }

    public synchronized List<UserActivity> findAll() {
        return new ArrayList<>(store.values());
    }
}
