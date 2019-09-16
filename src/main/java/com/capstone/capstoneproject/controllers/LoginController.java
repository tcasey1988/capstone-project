package com.capstone.capstoneproject.controllers;
import com.capstone.capstoneproject.models.User;
import com.capstone.capstoneproject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute(new User());
        model.addAttribute("title","User Login");
        return "user/login";
    }
}