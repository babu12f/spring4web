package com.nrbswift.spring4web.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Source;
import com.stripe.param.ChargeCaptureParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripeService {

    @Autowired
    public StripeService(@Value("${STRIPE_SECRET_KEY}") String API_SECRET_KEY) {
        Stripe.apiKey = API_SECRET_KEY;
    }

    public void createCustomer(String email) {
        Map<String, Object> customerMap = new HashMap<String, Object>();
        customerMap.put("description", "Example description");
        customerMap.put("email", email);
        customerMap.put("payment_method", "pm_card_visa"); // obtained via Stripe.js

        try {
            Customer customer = Customer.create(customerMap);
            System.out.println(customer);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }

    public void createCustomerToken(String email, String token) {
        Map<String, Object> customerMap = new HashMap<String, Object>();
        customerMap.put("description", "Example description");
        customerMap.put("email", email);
        customerMap.put("source", token);

        try {
            Customer customer = Customer.create(customerMap);
            System.out.println(customer);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }

    public void attachCustomerSource(String customerId, String token) throws StripeException {
        Map<String, Object> customerMap = new HashMap<String, Object>();
        customerMap.put("source", token);

        Customer customer = Customer.retrieve(customerId);
        Card card = (Card) customer.getSources().create(customerMap);
        System.out.println(card);
    }

    public Charge chargeNewCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

    public Charge chargeCustomer(String customerId, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

    public Charge chargeCustomerCard(String card, String customerId, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", card);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
}
