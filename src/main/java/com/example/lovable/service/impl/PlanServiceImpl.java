package com.example.lovable.service.impl;

import com.example.lovable.dto.subscription.PlanResponse;
import com.example.lovable.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
