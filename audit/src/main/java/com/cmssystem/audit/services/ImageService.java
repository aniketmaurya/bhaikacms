package com.cmssystem.audit.services;

import com.cmssystem.audit.dto.ImageUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageUploadResponse uploadImage(MultipartFile multipartFile, String type);
}
