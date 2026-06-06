package com.example.lovable.controller;

import com.example.lovable.dto.subscription.*;
import com.example.lovable.service.PlanService;
import com.example.lovable.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BillingController {

    private final PlanService planService;
    private final SubscriptionService subscriptionService;

    @GetMapping("/api/plans")
    public ResponseEntity<List<PlanResponse>> getPlans() {
        return ResponseEntity.ok(planService.getAllActivePlans());
    }

    @GetMapping("/api/me/subscription")
    public ResponseEntity<SubscriptionResponse> getSubscription() {
        Long userId = 1L; // Placeholder for authenticated user ID
        return ResponseEntity.ok(subscriptionService.getCurrentSubscription(userId));
    }

    @PostMapping("/api/stripe/checkout")
    public ResponseEntity<CheckoutResponse> createCheckoutResponse(
            @RequestBody CheckoutRequest request
    ) {
        Long userId = 1L; // Placeholder for authenticated user ID
        return ResponseEntity.ok(subscriptionService.createCheckoutSessionUrl(request, userId));
    }

    @PostMapping("/api/stripe/portal")
     public ResponseEntity<PortalResponse> openCustomerPortal() {
        Long userId = 1L; // Placeholder for authenticated user ID
        return ResponseEntity.ok(subscriptionService.openCustomerPortal(userId));
    }
}
