package com.basim.paymentservice.services;

import com.razorpay.RazorpayException;

public interface IPaymentService {
    String getPaymentLink(String orderId, Long amount, String phoneNumber, String name, String email) throws RazorpayException;
}
