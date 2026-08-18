package com.firstclub.membership.service;

import com.firstclub.membership.dto.ChangeTierRequest;
import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.model.Subscription;

public interface SubscriptionService {

    Subscription subscribe(SubscribeRequest request);

    Subscription updateTier(String userId, ChangeTierRequest request);

    Subscription cancel(String userId);

    Subscription getCurrentByUserId(String userId);
}
