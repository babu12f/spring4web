package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.braintree.BrainTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    BrainTreeService brainTreeService;

    @RequestMapping("/")
    public String showHome(Model model) {

        model.addAttribute("clientToken", brainTreeService.getClientToken());

        return "home";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    @ResponseBody
    public String checkout(HttpServletRequest request, HttpServletResponse response) {
        String nonceFromTheClient = request.getParameter("payment_method_nonce");

//        brainTreeService.applyPayment(nonceFromTheClient, 10);

//        brainTreeService.createCustomerWithPaymentMethod("bab@kmail.com", nonceFromTheClient);

//        brainTreeService.attachPaymentMethodToCustomer("573350126", nonceFromTheClient);
//        brainTreeService.applyPaymentCustomer("573350126", 3);

        brainTreeService.applyPaymentToPaymentMethod("8ypz722", 4);

        return "success";
    }

    @RequestMapping("/customer")
    @ResponseBody
    public String createCustomer(Model model) {

        brainTreeService.createCustomer("babu_12f@yahoo.com");

        return "customer crated successfully";
    }

    @RequestMapping("/getToken")
    @ResponseBody
    public String getToken(Model model) {
        return brainTreeService.getClientToken();
    }
}
