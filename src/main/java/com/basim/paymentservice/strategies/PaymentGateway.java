package com.basim.paymentservice.strategies;

import com.razorpay.RazorpayException;

public interface PaymentGateway {
    String createPaymentLink(String orderId, Long amount, String phoneNumber, String name, String email) throws RazorpayException;
}
