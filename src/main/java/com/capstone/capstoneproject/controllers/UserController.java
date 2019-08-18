package com.capstone.capstoneproject.controllers;

import com.capstone.capstoneproject.models.User;
import com.capstone.capstoneproject.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/*test comment*/
/*controller used for creating new user accounts*/
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserDao userDao;

    /*route for loading the form to add a new user*/
    @RequestMapping(value = "add")
    public String displayAddUser(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title","Add User");
        return "user/add";
    }

    /*saves the new user to database and returns the index page*/
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddUser(Model model, @ModelAttribute @Valid User user, Errors errors) {
        if(errors.hasErrors()){
            model.addAttribute(user);
            model.addAttribute("title","Add User");
            return "user/add";
        }
        model.addAttribute(user);
        userDao.save(user);
        model.addAttribute("title","Add User");
        return "user/index";
    }
}
