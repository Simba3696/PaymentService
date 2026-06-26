package com.basim.paymentservice.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stripe/webhook")
public class StripeWebhookController {

    @PostMapping
    public void handleWebhook(@RequestBody String payload) {
        System.out.println("Received Stripe Webhook:" + payload);
    }

}
