package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.dao.User;
import com.nrbswift.spring4web.dao.UserDAO;
import com.nrbswift.spring4web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String showHome(Model model) {

       userService.getUsers().forEach((User user) -> System.out.printf(user.toString()));

        return "home";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String saveUser(Model model) {

        userService.saveUser();

        return "home";
    }
}
