package org.ayush.paymentservice.services;

import org.ayush.paymentservice.paymentgateways.PaymentGateway;
import org.ayush.paymentservice.paymentgateways.stripe.StripePaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String createPaymentLink(Long orderID) throws Exception {
        return paymentGateway.generatePaymentLink(10000L, orderID.toString());
    }
}
