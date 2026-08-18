package com.firstclub.membership.controller;

import com.firstclub.membership.constant.Constants;
import com.firstclub.membership.dto.ChangeTierRequest;
import com.firstclub.membership.dto.MembershipResponse;
import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.service.MembershipResponseMapper;
import com.firstclub.membership.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_BASE_PATH)
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final MembershipResponseMapper membershipResponseMapper;

    @GetMapping("/users/{userId}/membership")
    public MembershipResponse getMembershipByUserId(@PathVariable String userId) {
        return membershipResponseMapper.toResponse(subscriptionService.getCurrentByUserId(userId));
    }

    @PostMapping("/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public MembershipResponse subscribe(@Valid @RequestBody SubscribeRequest request) {
        return membershipResponseMapper.toResponse(subscriptionService.subscribe(request));
    }

    @PatchMapping("/subscriptions/{userId}/tier")
    public MembershipResponse updateTier(@PathVariable String userId, @Valid @RequestBody ChangeTierRequest request) {
        return membershipResponseMapper.toResponse(subscriptionService.updateTier(userId, request));
    }

    @PostMapping("/subscriptions/{userId}/cancel")
    public MembershipResponse cancel(@PathVariable String userId) {
        return membershipResponseMapper.toResponse(subscriptionService.cancel(userId));
    }
}