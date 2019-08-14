package com.capstone.capstoneproject.controllers;

import com.capstone.capstoneproject.models.Doc;
import com.capstone.capstoneproject.models.data.DocDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
/*Controller for creating a document and saving to the database*/
@Controller
@RequestMapping("doc")
public class DocController {

    @Autowired
    DocDao docDao;

    @RequestMapping(value = "add")
    public String displayDocForm(Model model){
        model.addAttribute(new Doc());
        return "doc/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processDocForm(Model model, @ModelAttribute @Valid Doc doc, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute(doc);
            return "doc/add";
        }
        model.addAttribute(doc);
        docDao.save(doc);
        return "doc/index";
    }

}
