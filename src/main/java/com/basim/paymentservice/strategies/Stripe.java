package com.basim.paymentservice.strategies;

import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Stripe implements PaymentGateway {

    @Value("${stripe.apikey}")
    private String apiKey;

    @Override
    public String createPaymentLink(String orderId, Long amount, String phoneNumber, String name, String email) {

        try {
            com.stripe.Stripe.apiKey = apiKey;
            Price price = createPrice(amount);

            PaymentLinkCreateParams paymentLinkCreateParams = PaymentLinkCreateParams.builder().addLineItem(PaymentLinkCreateParams.LineItem.builder().setPrice(price.getId()).setQuantity(1L).build()).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder().setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT).setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder().setUrl("https://www.scaler.com").build()).build()).build();

            PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);

            return  paymentLink.getUrl();
        } catch (Exception e) {
            throw new RuntimeException("Error creating payment link " + e.getMessage());
        }
    }

    private Price createPrice(Long amount) {
        try {
            PriceCreateParams priceCreateParams = PriceCreateParams.builder().setCurrency("usd").setUnitAmount(amount).setRecurring(PriceCreateParams.Recurring.builder().setInterval(PriceCreateParams.Recurring.Interval.MONTH).build()).setProductData(PriceCreateParams.ProductData.builder().setName("Gold Plan").build()).build();

            Price price = Price.create(priceCreateParams);
            return price;
        } catch (Exception e) {
            throw new RuntimeException("Error creating price " + e.getMessage());
        }
    }
}
