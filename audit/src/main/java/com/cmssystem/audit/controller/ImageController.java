package com.cmssystem.audit.controller;

import com.cmssystem.audit.dto.ImageUploadResponse;
import com.cmssystem.audit.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/image")
@CrossOrigin(allowedHeaders = "*", origins = "*")
@Slf4j
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.POST, value = "/uploadImage")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam(value = "image") MultipartFile multipartFile,
                                                           @RequestParam(value = "type") String type) {

        //file,filetype,type
        String fileName = multipartFile == null ? "" : multipartFile.getOriginalFilename();
        String extension = fileName != null ? fileName.substring(fileName.lastIndexOf('.') + 1) : "";
        List<String> allowedExt = Arrays.asList("jpg", "jpeg", "png");

        if (allowedExt.contains(extension)) {
            try {
                return ResponseEntity.ok(imageService.uploadImage(multipartFile, type));
            } catch (Exception e) {
                log.error("Error: {}", e.getMessage());
                return ResponseEntity.ok(new ImageUploadResponse(fileName, ""));
            }
        }
        return ResponseEntity.ok(new ImageUploadResponse(fileName, ""));
    }

}
