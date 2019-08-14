package com.capstone.capstoneproject.controllers;

import com.capstone.capstoneproject.models.data.DocDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("list")
public class ListController {

    @Autowired
    DocDao docDao;

    @RequestMapping(value = "all")
    public String index(Model model){
        model.addAttribute("docs", docDao.findAll());
        return "list/index";
    }
}
