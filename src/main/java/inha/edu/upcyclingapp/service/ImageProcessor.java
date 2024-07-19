package inha.edu.upcyclingapp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageProcessor {
    void upload(String key, MultipartFile image) throws IOException;
    byte[] findByKey(String key);
}
