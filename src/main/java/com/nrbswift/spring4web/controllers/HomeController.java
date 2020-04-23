package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.dao.User;
import com.nrbswift.spring4web.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserDAO userDao;

    @RequestMapping("/")
    public String showHome(Model model) {

//        User u = new User("admin2", "shofiullah babor", "babu@gmal.com", "secret", true);
//        userDao.createUser(u);

        List<User> users = userDao.getAll();

        for (User user:users) {
            System.out.println(user);
        }

//        model.addAttribute("users", users);
        model.addAttribute("name", "Shofiullah babor");

        return "home";
    }

    @RequestMapping("/profile")
    @ResponseBody
    public String restrictionArea() {
        return "welcome authenticated user";
    }
}
