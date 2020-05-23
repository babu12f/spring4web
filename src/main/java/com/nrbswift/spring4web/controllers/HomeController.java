package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.dao.User;
import com.nrbswift.spring4web.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserDAO userDao;

    @RequestMapping("/")
    public String showHome(Model model) {

        model.addAttribute("name", "Shofiullah babor");

        return "home";
    }
}
