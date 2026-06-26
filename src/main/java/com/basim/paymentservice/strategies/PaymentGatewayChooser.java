package com.basim.paymentservice.strategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChooser {

    @Autowired
    private Stripe stripe;

    @Autowired
    private Razorpay razorpay;

    @Autowired
    private PhonePe phonePe;

    public PaymentGateway choosePaymentGateway(String gatewayName) {
        switch (gatewayName.toLowerCase()) {
            case "stripe": {
                return stripe;
            }
            case "razorpay": {
                return razorpay;
            }
            case "phonepe": {
                return phonePe;
            }
            default: {
                throw new IllegalArgumentException("Unsupported payment gateway: " + gatewayName);
            }
        }
    }
}
