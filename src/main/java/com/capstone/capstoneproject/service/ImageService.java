package com.capstone.capstoneproject.service;

import com.capstone.capstoneproject.models.Image;
import com.capstone.capstoneproject.models.data.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageService {

    private static String UPLOAD_ROOT = "C:\\Users\\Tom\\IdeaProjects\\capstone-project\\src\\main\\resources\\static\\images";

    private ImageDao imageDao;
    private ResourceLoader resourceLoader;

    @Autowired
    public ImageService(ImageDao imageDao, ResourceLoader resourceLoader){
        this.imageDao = imageDao;
        this.resourceLoader = resourceLoader;
    }

    public void createImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
            imageDao.save(new Image(file.getOriginalFilename()));
        }
    }
}
