package com.cmssystem.audit.controller;

import com.cmssystem.audit.dto.ImageUploadResponse;
import com.cmssystem.audit.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/image")
@CrossOrigin(allowedHeaders = "*", origins = "*")
@Slf4j
public class ImageController {

    private static final double MAX_IMAGE_SIZE = 5 * Math.pow(1024, 2);
    private static final double MAX_VIDEO_SIZE = 5 * Math.pow(1024, 3);
    private static final List<String> ALLOWED_RES = Arrays.asList("780*780","1000*10000", "1200*1200");
    private static final List<String> ALLOWED_IMAGE_EXT = Arrays.asList("jpg", "jpeg", "png");
    private static final List<String> ALLOWED_VIDEO_EXT = Arrays.asList("mp4", "3gp", "webm", "mpeg4");
    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.POST, value = "/uploadImage")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam(value = "image") MultipartFile multipartFile,
                                                           @RequestParam(value = "type") String type) {

        String fileName = multipartFile == null ? "" : multipartFile.getOriginalFilename();
        String extension = fileName != null ? fileName.substring(fileName.lastIndexOf('.') + 1) : "";
        List<String> allowedExt = Arrays.asList("jpg", "jpeg", "png");

        if (allowedExt.contains(extension)) {
            try {
                return ResponseEntity.ok(imageService.uploadImage(multipartFile, "image", type));
            } catch (Exception e) {
                log.error("Error: {}", e.getMessage());
                return ResponseEntity.ok(new ImageUploadResponse(fileName, ""));
            }
        }
        return ResponseEntity.ok(new ImageUploadResponse(fileName, ""));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public ResponseEntity<ImageUploadResponse> uploadFile(@RequestParam(value = "file") MultipartFile multipartFile,
                                                          @RequestParam(value = "filetype") String fileType,
                                                          @RequestParam(value = "type") String type) {


        try {
            if (multipartFile == null)
                return ResponseEntity.ok(new ImageUploadResponse("File Not Received", ""));
            else if (!checkForExtension(multipartFile, fileType)) {
                return ResponseEntity.ok(new ImageUploadResponse("File Type Not Supported", ""));
            } else if (!checkForSize(multipartFile, fileType)) {
                return ResponseEntity.ok(new ImageUploadResponse("File Size Exceeded", ""));
            } else if (fileType.equalsIgnoreCase("image") && !checkForResolution(multipartFile)) {
                return ResponseEntity.ok(new ImageUploadResponse("Image Resolution Incorrect", ""));
            }
            return ResponseEntity.ok(imageService.uploadImage(multipartFile, fileType, type));
        } catch (Exception e) {
            return ResponseEntity.ok(new ImageUploadResponse("Error: " + e.getMessage(), ""));
        }

    }

    private boolean checkForSize(MultipartFile multipartFile, String fileType) {
        if (fileType.equalsIgnoreCase("image"))
            return multipartFile.getSize() <= MAX_IMAGE_SIZE;
        return multipartFile.getSize() <= MAX_VIDEO_SIZE;
    }

    private boolean checkForResolution(MultipartFile multipartFile) throws IOException {

        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(multipartFile.getBytes()));

        int height = originalImage.getHeight();
        int width = originalImage.getWidth();

        log.warn("Height:: {} WIDTH:: {}", height, width);

        return ALLOWED_RES.contains(height + "*" + width);
    }

    private boolean checkForExtension(MultipartFile multipartFile, String fileType) {

        String fileName = multipartFile == null ? "" : multipartFile.getOriginalFilename();
        String extension = fileName != null ? fileName.substring(fileName.lastIndexOf('.') + 1) : "";

        if (fileType.equalsIgnoreCase("image"))
            return ALLOWED_IMAGE_EXT.contains(extension);
        if (fileType.equalsIgnoreCase("video"))
            return ALLOWED_VIDEO_EXT.contains(extension);
        return false;
    }

}
