package org.ayush.paymentservice.paymentgateways.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.ayush.paymentservice.paymentgateways.PaymentGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {

    @Value("${stripe.secretKey}")
    private String stripeApiKey;

    @Override
    public String generatePaymentLink(Long amount, String orderID) throws StripeException {
        Stripe.apiKey = stripeApiKey;

        ProductCreateParams productCreateParams =
                ProductCreateParams.builder().setName("Gold Plan").build();
        Product product = Product.create(productCreateParams);

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProduct(product.getId())
                        .build();
        Price price = Price.create(params);


        PaymentLinkCreateParams paymentLinkParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        ).setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder().setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT).setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder().setUrl("https://scaler.com?orderId="+orderID).build()).build()
                        ).build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkParams);
        return paymentLink.getUrl();
    }
}
