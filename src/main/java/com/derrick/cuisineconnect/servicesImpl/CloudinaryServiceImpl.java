package com.derrick.cuisineconnect.servicesImpl;

import com.cloudinary.Cloudinary;
import com.derrick.cuisineconnect.services.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;
    @Override
    public String uploadImage(MultipartFile image, String folderName) {
        try{
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", folderName);
            Map uploadFile = cloudinary.uploader().upload(image.getBytes(), options);
            String publicId = (String) uploadFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
