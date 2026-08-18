package com.firstclub.membership.repository;

import com.firstclub.membership.model.Subscription;
import com.firstclub.membership.enums.SubscriptionStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SubscriptionRepository extends InMemoryRepository<Subscription> {

    public synchronized Optional<Subscription> findActiveByUserId(String userId) {
        return findAll().stream()
                .filter(s -> s.getUserId().equals(userId) && s.getStatus() == SubscriptionStatus.ACTIVE)
                .findFirst();
    }
}
