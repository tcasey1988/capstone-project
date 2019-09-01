package com.capstone.capstoneproject.controllers;

import com.capstone.capstoneproject.models.Document;
import com.capstone.capstoneproject.models.data.DocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("document")
public class DocumentController {

    @Autowired
    DocumentDao documentDao;

    /*route for loading the form for adding documents and procedures*/
    @RequestMapping(value = "add")
    public String displayAddDocument(Model model){
        model.addAttribute(new Document());
        model.addAttribute("title","Add Procedure");
        return "document/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddDcoument(Model model, @ModelAttribute @Valid Document document, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute(document);
            model.addAttribute("title","Add Procedure");
            return "document/add";
        }
        model.addAttribute(document);
        documentDao.save(document);
        model.addAttribute("title","Add Procedure");
        return "redirect:/document/view/" + document.getId();
    }

    /*view a form by clicking a link on the home page*/
    @RequestMapping(value = "view/{documentId}", method = RequestMethod.GET)
    public String viewDocument(Model model, @PathVariable int documentId){
        Optional<Document> optDoc = documentDao.findById(documentId);
        Document myDocument = new Document();
        if (optDoc.isPresent()){
            myDocument = optDoc.get();
        }
        model.addAttribute(myDocument);
        model.addAttribute("title","View Procedure");
        return "document/view";
    }
}
