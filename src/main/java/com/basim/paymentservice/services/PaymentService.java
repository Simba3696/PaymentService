package com.basim.paymentservice.services;

import com.basim.paymentservice.strategies.PaymentGateway;
import com.basim.paymentservice.strategies.PaymentGatewayChooser;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements  IPaymentService {

    @Autowired
    private PaymentGatewayChooser paymentGatewayChooser;

    @Override
    public String getPaymentLink(String orderId, Long amount, String phoneNumber, String name, String email) throws RazorpayException {

        PaymentGateway paymentGateway = paymentGatewayChooser.choosePaymentGateway("stripe");

        return paymentGateway.createPaymentLink(orderId, amount, phoneNumber, name, email);
    }
}
