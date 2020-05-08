package com.nrbswift.spring4web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String showHome() {
        return "home";
    }

    @RequestMapping("/chat")
    public String showChat() {
        return "chat";
    }

    @RequestMapping("/chat2")
    public String showChat2() {
        return "chat2";
    }
}
