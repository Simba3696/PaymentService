package com.basim.paymentservice.strategies;

import org.springframework.stereotype.Component;

@Component
public class PhonePe implements PaymentGateway {

    @Override
    public String createPaymentLink(String orderId, Long amount, String phoneNumber, String name, String email) {
        return null;
    }
}
