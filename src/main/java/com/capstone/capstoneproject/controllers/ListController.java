package com.capstone.capstoneproject.controllers;

import com.capstone.capstoneproject.models.data.DocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("list")
public class ListController {
    @Autowired
    DocumentDao documentDao;

    @RequestMapping(value = "all")
    public String listDocuments(Model model){
        model.addAttribute("documents", documentDao.findAll());
        model.addAttribute("title","All Procedures");
        return "list/index";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchDocuments(Model model, @RequestParam String searchTerm){
        model.addAttribute("documents", documentDao.findBySearchTerm(searchTerm));
        model.addAttribute("title","Search Results");
        return "list/index";
    }
}
