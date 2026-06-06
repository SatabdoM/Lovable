package com.example.lovable.entity;

import com.example.lovable.entity.enums.SubscriptionStatus;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Subscription {
    Long id;
    User user;
    Plan plan;

    SubscriptionStatus status;

    String stripeCustomerId;
    String stripeCustomerName;

    Instant currentPeriodStart;
    Instant currentPeriodEnd;
    Instant cancelAtPeriodEnd;

    Instant createdAt;
    Instant updatedAt;
}
