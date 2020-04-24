package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.dao.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String showHome(Model model) {
        model.addAttribute("name", "Shofiullah babor");

        return "home";
    }

    @RequestMapping("/profile")
    @ResponseBody
    public String restrictionArea() {

//        String logedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return "welcome <b>"+appUser+"</b>";
    }
}
