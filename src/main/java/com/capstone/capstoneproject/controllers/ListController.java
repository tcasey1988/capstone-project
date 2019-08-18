package com.capstone.capstoneproject.controllers;

import com.capstone.capstoneproject.models.data.DocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("list")
public class ListController {

    @Autowired
    DocumentDao documentDao;

    /*loads all saved documents as links. Temporary home page*/
    @RequestMapping(value = "all")
    public String listDocuments(Model model){
        model.addAttribute("documents", documentDao.findAll());
        model.addAttribute("title","All Procedures");
        return "list/index";
    }
}
