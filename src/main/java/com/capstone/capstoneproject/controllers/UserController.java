package com.capstone.capstoneproject.controllers;

import com.capstone.capstoneproject.models.User;
import com.capstone.capstoneproject.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*test comment*/
/*controller used for creating new user accounts*/
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserDao userDao;

    /*route for loading the form to add a new user*/
    @RequestMapping(value = "add")
    public String add(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    /*saves the new user to database and returns the index page*/
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user) {
        model.addAttribute(user);
        userDao.save(user);
        return "user/index";
    }
}
