package com.derrick.cuisineconnect.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    String uploadImage(MultipartFile image, String folderName) throws IOException;

}
