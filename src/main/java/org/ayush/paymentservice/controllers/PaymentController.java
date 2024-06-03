package org.ayush.paymentservice.controllers;

import org.ayush.paymentservice.dtos.CreatePaymentLinkRequestDto;
import org.ayush.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping
    public String createPaymentLink(@RequestBody CreatePaymentLinkRequestDto requestDto){
        return paymentService.createPaymentLink(requestDto.getOrderID());
    }
}
