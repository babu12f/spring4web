package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.dao.AppUser;
import com.nrbswift.spring4web.dao.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    AppUserDao appUserDao;

    @RequestMapping("/")
    public String showHome(Model model) {
        model.addAttribute("name", "Shofiullah babor");

        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String signUpUser(@ModelAttribute AppUser appUser) {
        appUser.setRole("ROLE_USER");

        appUserDao.registerUser(appUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(appUser,
                appUser.getPassword(), appUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }

    @RequestMapping("/profile")
    @ResponseBody
    public String restrictionArea() {

//        String logedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return "welcome <b>"+appUser+"</b>";
    }

    @RequestMapping("/both")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @ResponseBody
    public String bothHome() {
        return "both user and admin Home";
    }

    @RequestMapping("/adhome")
    @Secured({"ROLE_ADMIN"})
    @ResponseBody
    public String adminHome() {
        return "admin Home";
    }

    @RequestMapping("/ushome")
    @Secured({"ROLE_USER"})
    @ResponseBody
    public String userHome() {
        return "user Home";
    }
}
