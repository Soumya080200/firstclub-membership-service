package com.firstclub.membership.controller;

import com.firstclub.membership.constant.Constants;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipTier;
import com.firstclub.membership.service.MembershipCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.API_BASE_PATH)
@RequiredArgsConstructor
public class MembershipCatalogController {

    private final MembershipCatalogService membershipCatalogService;

    @GetMapping("/membership/plans")
    public List<MembershipPlan> getPlans() {
        return membershipCatalogService.getAllPlans();
    }

    @GetMapping("/membership/tiers")
    public List<MembershipTier> getTiers() {
        return membershipCatalogService.getAllTiers();
    }

    @GetMapping("/users/{userId}/eligible-tiers")
    public List<MembershipTier> getEligibleTiersByUserId(@PathVariable String userId) {
        return membershipCatalogService.getEligibleTiersByUserId(userId);
    }
}
