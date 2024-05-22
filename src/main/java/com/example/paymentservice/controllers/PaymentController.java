package com.example.paymentservice.controllers;

import com.example.paymentservice.dto.CreatePaymentLinkRequestDto;
import com.example.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }
    @PostMapping("/")
    public String createPaymentLink(CreatePaymentLinkRequestDto requestDto){
        String payment=null;
        try {
            payment = paymentService.createPaymentLink(requestDto.getOrderid());
        }catch (Exception e){
            System.out.println("Exception occured");
        }
        return payment;
    }
}
