package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.stripe.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    StripeService stripeService;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/")
    public String showHome(Model model) {
        model.addAttribute("name", "Shofiullah babor");

        return "home";
    }

    @RequestMapping("/customer")
    @ResponseBody
    public String createCustomerHome() {
        stripeService.createCustomer("new1@gmail.com");

        return "customer created";
    }

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    @ResponseBody
    public String showCharge(@RequestBody MultiValueMap<String, String> formData) throws Exception {
        String token = formData.get("stripeToken").get(0);

//        stripeService.createCustomerToken("new_2@kmail.com", token);

//        stripeService.chargeNewCard(token, 2);

//        stripeService.chargeCustomer("cus_HHsX70xFU57jKo", 2);

//        stripeService.attachCustomerSource("cus_HHsX70xFU57jKo", token);
//        stripeService.chargeCustomerCard("card_1GjJAqGhqORJhDAVPxNSCvz6","cus_HHsX70xFU57jKo", 2);


        return "home";
    }

    @RequestMapping("/checkout")
    public String showCheckout(Model model) {
        model.addAttribute("amount", 5 * 100); // In cents
        model.addAttribute("stripePublicKey", stripePublicKey);

        return "checkout";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    @ResponseBody
    public String chargeCard(HttpServletRequest request) throws Exception {
        String token = request.getParameter("stripeToken");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        stripeService.chargeNewCard(token, amount);
        return "success :)";
    }
}
