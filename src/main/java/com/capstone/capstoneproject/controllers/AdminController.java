package com.capstone.capstoneproject.controllers;

import com.capstone.capstoneproject.models.User;
import com.capstone.capstoneproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "")
    public String index(){
        return "admin/index";
    }

    @RequestMapping(value="registration", method = RequestMethod.GET)
    public String registration(Model model, @ModelAttribute User user){
        model.addAttribute("user", user);
        model.addAttribute("title","User Registration");
        return "admin/registration";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String createNewUser(Model model, @ModelAttribute @Valid User user, BindingResult bindingResult) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("title", "User Registration");
            return "admin/registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", user);
            model.addAttribute("title", "User Registration");
            return "admin/registration";
        }
    }


    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String showDeleteUserForm(){
        return "admin/delete";
    }
}
