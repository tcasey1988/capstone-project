package com.capstone.capstoneproject.controllers;

import com.capstone.capstoneproject.models.User;
import com.capstone.capstoneproject.models.data.UserDao;
import com.capstone.capstoneproject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "")
    public String index() {
        return "admin/index";
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String registration(Model model, @ModelAttribute User user) {
        model.addAttribute("user", user);
        model.addAttribute("title", "User Registration");
        return "admin/registration";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String createNewUser(Model model, @ModelAttribute @Valid User user, BindingResult bindingResult) {
        User userExists = loginService.findUserByEmail(user.getEmail());
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
            loginService.saveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", user);
            model.addAttribute("title", "User Registration");
            return "admin/registration";
        }
    }
    @RequestMapping(value = "edit-list")
    public String listDocuments(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "admin/edit-list";
    }

    @RequestMapping(value = "edit/{userId}", method = RequestMethod.GET)
    public String displayUserEdit(Model model, @PathVariable int userId){
        Optional<User>optUser = userDao.findById(userId);
        User editUser = new User();
        if (optUser.isPresent()){
            editUser = optUser.get();
        }
        model.addAttribute("user", editUser);
        return "admin/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editUser(Model model, int userId, @RequestParam String name, String lastName, String email,
                           String password, @ModelAttribute @Valid User user, Errors errors){
        Optional<User>optUser = userDao.findById(userId);
        User editUser = new User();
        if (optUser.isPresent()){
            editUser = optUser.get();
        }
        editUser.setName(name);
        editUser.setLastName(lastName);
        editUser.setEmail(email);
        editUser.setPassword(password);
        loginService.saveUser(editUser);
        return "admin/index";

    }


    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String showDeleteUserForm(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "admin/delete";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String processDeleteUserForm(@RequestParam int[] userIds) {

        for (int userId : userIds) {
            userDao.deleteById(userId);
        }

        return "redirect:";
    }



}
