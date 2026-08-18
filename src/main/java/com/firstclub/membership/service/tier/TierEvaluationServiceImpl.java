package com.firstclub.membership.service.tier;

import com.firstclub.membership.model.MembershipTier;
import com.firstclub.membership.model.UserActivity;
import com.firstclub.membership.enums.CriterionType;
import com.firstclub.membership.repository.UserActivityRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TierEvaluationServiceImpl implements TierEvaluationService {

    private final Map<CriterionType, TierCriterionStrategy> strategiesByType;
    private final UserActivityRepository userActivityRepository;

    public TierEvaluationServiceImpl(List<TierCriterionStrategy> strategies, UserActivityRepository userActivityRepository) {
        this.strategiesByType = strategies.stream()
                .collect(Collectors.toMap(TierCriterionStrategy::getType, Function.identity()));
        this.userActivityRepository = userActivityRepository;
    }

    @Override
    public boolean isEligible(MembershipTier tier, String userId) {
        UserActivity activity = userActivityRepository.findByUserId(userId).orElseGet(() -> UserActivity.empty(userId));
        return tier.getCriteria().stream()
                .allMatch(criterion -> getStrategyOrThrow(criterion.getType()).isSatisfied(criterion, activity));
    }

    private TierCriterionStrategy getStrategyOrThrow(CriterionType type) {
        TierCriterionStrategy strategy = strategiesByType.get(type);
        if (strategy == null) {
            throw new IllegalStateException("No tier criterion strategy registered for type " + type);
        }
        return strategy;
    }

    @Override
    public List<MembershipTier> eligibleTiers(List<MembershipTier> allTiers, String userId) {
        return allTiers.stream()
                .filter(tier -> isEligible(tier, userId))
                .sorted(Comparator.comparingInt(MembershipTier::getRank))
                .toList();
    }
}
