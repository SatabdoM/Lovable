package com.example.lovable.service.impl;

import com.example.lovable.dto.subscription.CheckoutRequest;
import com.example.lovable.dto.subscription.CheckoutResponse;
import com.example.lovable.dto.subscription.PortalResponse;
import com.example.lovable.dto.subscription.SubscriptionResponse;
import com.example.lovable.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }

    @Override
    public void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId){
    }
}
