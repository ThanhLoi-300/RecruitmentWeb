package com.example.springwebapp.controller;

import com.example.springwebapp.model.Static.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String getHomeView (Model model) {
        model.addAttribute("username", User.name);
        return "index";
    }
    @GetMapping(value = "/logout")
    public String logout () {
        User.id = -1;
        User.role = "";
        User.name = null;
        return "redirect:/";
    }

}
