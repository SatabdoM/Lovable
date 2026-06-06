package com.example.lovable.service;


import com.example.lovable.dto.subscription.CheckoutRequest;
import com.example.lovable.dto.subscription.CheckoutResponse;
import com.example.lovable.dto.subscription.PortalResponse;
import com.example.lovable.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
