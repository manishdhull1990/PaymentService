package com.example.paymentservice.services;

import com.example.paymentservice.PaymentServiceApplication;
import com.example.paymentservice.paymentgateways.PaymentGateway;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {
    private PaymentGateway paymentGateway;
    PaymentService(PaymentGateway paymentGateway){
        this.paymentGateway=paymentGateway;
    }
    public String createPaymentLink(Long orderId) throws RazorpayException {
        return paymentGateway.createPaymentLink(orderId);
    }
}
