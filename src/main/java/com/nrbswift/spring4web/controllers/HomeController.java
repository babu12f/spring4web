package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.mq.MessageReceiver;
import com.nrbswift.spring4web.mq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Autowired
    MessageSender messageSender;

    @Autowired
    MessageReceiver messageReceiver;

    @RequestMapping("/")
    public String showHome(Model model) {
        model.addAttribute("name", "Shofiullah babor");

        return "home";
    }

    @RequestMapping("/send")
    @ResponseBody
    public String sendMessageToQueue(@RequestParam(value = "msg", required = false) String message) {
        if (message == null) {
            messageSender.sendMessage("any message");
        }
        else {
            messageSender.sendMessage(message);
        }

        return "message add Successfully :)";
    }
    @RequestMapping("/receive")
    @ResponseBody
    public String receiveMessageToQueue() {
        String message = messageReceiver.receiveMessage();

        return "message from queue : >>>> " + message;
    }
}
