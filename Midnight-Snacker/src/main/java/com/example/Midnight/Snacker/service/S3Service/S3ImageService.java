package com.example.Midnight.Snacker.service.S3Service;

import org.springframework.web.multipart.MultipartFile;

public interface S3ImageService {
    String upload(MultipartFile image);
    void deleteImageFromS3(String imageAddress);
}
