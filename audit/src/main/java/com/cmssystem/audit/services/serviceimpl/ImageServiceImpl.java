package com.cmssystem.audit.services.serviceimpl;

import com.cmssystem.audit.dto.ImageUploadResponse;
import com.cmssystem.audit.services.ImageService;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Override
    public ImageUploadResponse uploadImage(MultipartFile multipartFile, String fileType, String type) {

        log.warn("File: {} filetype: {} and type: {}", multipartFile, fileType, type);
        try {
            Credentials credentials = GoogleCredentials
                    .fromStream(new FileInputStream("src/chatwithexperts-88760-a26f3f484a87.json"));
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials)
                    .setProjectId("chatwithexperts-88760").build().getService();

            BlobInfo blobInfo = storage.create(
                    BlobInfo.newBuilder("bhaikabucket",
                            fileType.concat("/".concat(type.concat("/".concat(String.valueOf(System.currentTimeMillis()))))))
                            .setAcl(new ArrayList<>(Collections.singletonList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                            .build(),
                    multipartFile.getBytes());

            return new ImageUploadResponse(multipartFile.getOriginalFilename()+ " Uploaded!", blobInfo.getMediaLink());
        } catch (Exception e) {
            log.warn("Error: {}", e.getMessage());
            return new ImageUploadResponse("Error: "+e.getMessage(), "");
        }
    }

}
