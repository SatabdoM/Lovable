package com.example.lovable.service.impl;

import com.example.lovable.dto.subscription.PlanLimitsResponse;
import com.example.lovable.dto.subscription.UsageTodayResponse;
import com.example.lovable.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
