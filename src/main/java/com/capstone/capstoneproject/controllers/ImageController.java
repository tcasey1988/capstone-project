package com.capstone.capstoneproject.controllers;

import com.capstone.capstoneproject.models.data.ImageDao;
import com.capstone.capstoneproject.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    ImageDao imageDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("images", imageDao.findAll());
        model.addAttribute("title","Images");
        return "image/index";
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public String createFile(Model model, @RequestParam("file")MultipartFile file){
        try {
            imageService.createImage(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("images", imageDao.findAll());
        model.addAttribute("title","Images");
        return "image/index";
    }
}

