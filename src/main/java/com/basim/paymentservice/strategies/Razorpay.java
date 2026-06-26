package com.basim.paymentservice.strategies;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Razorpay implements  PaymentGateway {

    @Autowired
    private RazorpayClient instance;

    @Override
    public String createPaymentLink(String orderId, Long amount, String phoneNumber, String name, String email) throws RazorpayException {

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", amount);
        paymentLinkRequest.put("currency", "INR");
        paymentLinkRequest.put("accept_partial", true);
        paymentLinkRequest.put("first_min_partial_amount", 100);
        paymentLinkRequest.put("expire_by", System.currentTimeMillis() + 600000);
        paymentLinkRequest.put("reference_id", orderId);
        paymentLinkRequest.put("description", "Payment for Payment Service Class");

        JSONObject customer = new JSONObject();
        customer.put("name", name);
        customer.put("contact", phoneNumber);
        customer.put("email", email);

        paymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("sms", true);
        notify.put("email", true);

        paymentLinkRequest.put("reminder_enable", true);

        JSONObject notes = new JSONObject();
        notes.put("policy_name", "Jeevan Bima");
        paymentLinkRequest.put("notes", notes);
        paymentLinkRequest.put("callback_url", "https://google.com");
        paymentLinkRequest.put("callback_method", "get");

        PaymentLink paymentLink = instance.paymentLink.create(paymentLinkRequest);

        return paymentLink.get("short_url");
    }
}
