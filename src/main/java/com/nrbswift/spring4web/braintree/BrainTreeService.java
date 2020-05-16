package com.nrbswift.spring4web.braintree;

import com.braintreegateway.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class BrainTreeService {
    /* ********Braintree Payment Transaction Credentials ************ */

    @Value("${BRAIN_TREE_MERCHANT_ID}")
    private String MERCHANT_ID;

    @Value("${BRAIN_TREE_PUBLIC_KEY}")
    private String PUBLIC_KEY;

    @Value("${BRAIN_TREE_PRIVATE_KEY}")
    private String PRIVATE_KEY;

    public BrainTreeService() {
    }

    /* ********Braintree Payment Transaction Credentials ************ */

    public BraintreeGateway getBrainTreeGateway() {
        return new BraintreeGateway(Environment.SANDBOX, MERCHANT_ID, PUBLIC_KEY, PRIVATE_KEY);
    }

    public String getClientToken() {
        ClientTokenRequest clientTokenRequest = new ClientTokenRequest();
        String clientToken = getBrainTreeGateway().clientToken().generate(clientTokenRequest);
        return clientToken;
    }

    public void applyPayment(String nonceFromTheClient, double amount) {
        TransactionRequest request = new TransactionRequest()
                .amount(BigDecimal.valueOf(amount))
                .paymentMethodNonce(nonceFromTheClient)
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = getBrainTreeGateway().transaction().sale(request);
        System.out.println(result);
    }

    public void createCustomer(String email) {
        CustomerRequest request = new CustomerRequest()
                .firstName("babu")
                .email(email);
        Result<Customer> result = getBrainTreeGateway().customer().create(request);

        System.out.println(result.isSuccess());
        System.out.println(result.getTarget().getId());
    }

    public void createCustomerWithPaymentMethod(String email, String nonceFromTheClient) {
        CustomerRequest request = new CustomerRequest()
                .firstName("babu")
                .email(email)
                .paymentMethodNonce(nonceFromTheClient);
        Result<Customer> result = getBrainTreeGateway().customer().create(request);

        Customer customer = result.getTarget();
        System.out.println(customer.getId());;

        System.out.println(customer.getPaymentMethods().get(0).getToken());;
    }

    public void attachPaymentMethodToCustomer(String customerId, String nonceFromTheClient) {
        PaymentMethodRequest request = new PaymentMethodRequest()
                .customerId(customerId)
                .paymentMethodNonce(nonceFromTheClient);

        Result<? extends PaymentMethod> result = getBrainTreeGateway().paymentMethod().create(request);

        PaymentMethod paymentMethod = result.getTarget();

        System.out.println(paymentMethod.getToken());;
    }

    public void applyPaymentCustomer(String customerId, double amount) {
        TransactionRequest request = new TransactionRequest()
                .customerId(customerId)
                .amount(BigDecimal.valueOf(amount))
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = getBrainTreeGateway().transaction().sale(request);
        System.out.println(result);
    }

    public void applyPaymentToPaymentMethod(String paymentMethodToken, double amount) {
        TransactionRequest request = new TransactionRequest()
                .paymentMethodToken(paymentMethodToken)
                .amount(BigDecimal.valueOf(amount))
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = getBrainTreeGateway().transaction().sale(request);
        System.out.println(result);
    }

}
