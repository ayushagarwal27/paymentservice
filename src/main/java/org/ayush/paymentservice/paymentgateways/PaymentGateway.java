package org.ayush.paymentservice.paymentgateways;

public interface PaymentGateway {
    String generatePaymentLink(Long amount, String orderID) throws Exception;
}
