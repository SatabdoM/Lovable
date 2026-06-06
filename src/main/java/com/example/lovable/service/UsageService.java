package com.example.lovable.service;

import com.example.lovable.dto.subscription.PlanLimitsResponse;
import com.example.lovable.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
