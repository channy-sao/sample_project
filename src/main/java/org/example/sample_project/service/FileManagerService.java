package org.example.sample_project.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileManagerService {
    String upload(MultipartFile file, String folder);
    void delete(String fileUrl);
}
