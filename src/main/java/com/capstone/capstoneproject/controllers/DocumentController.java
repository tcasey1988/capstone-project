package com.capstone.capstoneproject.controllers;

import com.capstone.capstoneproject.models.Document;
import com.capstone.capstoneproject.models.data.DocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("document")
public class DocumentController {

    @Autowired
    DocumentDao documentDao;

    @RequestMapping(value = "add")
    public String displayAddDocument(Model model){
        model.addAttribute(new Document());
        model.addAttribute("title","Add Procedure");
        return "document/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddDocument(Model model, @ModelAttribute @Valid Document document, Errors errors){
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

    @RequestMapping(value = "edit/{documentId}", method = RequestMethod.GET)
    public String displayEdit(Model model, @PathVariable int documentId){
        Optional<Document> optDoc = documentDao.findById(documentId);
        Document myDocument = new Document();
        if (optDoc.isPresent()){
            myDocument = optDoc.get();
        }
        model.addAttribute("document", myDocument);
        model.addAttribute("title","Edit Procedure");
        return "document/edit";
    }

    @RequestMapping(value = "edit", method  = RequestMethod.POST)
    public String editDocument(Model model, int documentId, @RequestParam String title, String content, String author,
                               @ModelAttribute @Valid Document document, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("document",document);
            model.addAttribute("title","Edit Procedure");
            return "document/edit/{documentId}";
        }

        Optional<Document> optDoc = documentDao.findById(documentId);
        Document editDocument = new Document();
        if (optDoc.isPresent()){
            editDocument = optDoc.get();
        }
        editDocument.setTitle(title);
        editDocument.setContent(author);
        editDocument.setContent(content);
        documentDao.save(editDocument);
        return "redirect:/document/view/" + editDocument.getId();
    }

    @RequestMapping(value = "view/{documentId}", method = RequestMethod.POST)
    public String deleteDocument(Model model, @PathVariable int documentId){
        Optional<Document> optDoc = documentDao.findById(documentId);
        Document myDocument = new Document();
        if (optDoc.isPresent()){
            myDocument = optDoc.get();
        }

        documentDao.delete(myDocument);
        model.addAttribute("documents", documentDao.findAll());
        return "list/index";
    }

}
