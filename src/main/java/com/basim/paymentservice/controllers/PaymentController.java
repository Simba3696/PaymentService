package com.basim.paymentservice.controllers;

import com.basim.paymentservice.dtos.PaymentRequestDTO;
import com.basim.paymentservice.services.IPaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping
    public String getPaymentLink(@RequestBody PaymentRequestDTO requestDTO) throws RazorpayException {
        return paymentService.getPaymentLink(requestDTO.getOrderId(), requestDTO.getAmount(), requestDTO.getPhoneNumber(), requestDTO.getName(), requestDTO.getEmail());
    }
}
