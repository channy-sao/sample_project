package org.example.sample_project.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.sample_project.properties.FileStorageProperties;
import org.example.sample_project.service.FileManagerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class LocalFileManagerService implements FileManagerService {

  private final FileStorageProperties properties;

  @Override
  public String upload(MultipartFile file, String folder) {

    if (file == null || file.isEmpty()) {
      return null;
    }

    try {
      Path folderPath = Paths.get(properties.getLocation(), folder);

      Files.createDirectories(folderPath);

      String originalFilename = file.getOriginalFilename();

      String extension = "";

      if (originalFilename != null && originalFilename.contains(".")) {
        extension = originalFilename.substring(originalFilename.lastIndexOf("."));
      }

      String fileName = UUID.randomUUID() + extension;

      Path filePath = folderPath.resolve(fileName);

      Files.copy(file.getInputStream(), filePath);

      return filePath.toUri().toString();

    } catch (IOException e) {
      throw new RuntimeException("Failed to upload file", e);
    }
  }

  @Override
  public void delete(String fileUrl) {

    if (fileUrl == null || fileUrl.isBlank()) {
      return;
    }

    try {

      Path filePath = Paths.get(fileUrl);

      Files.deleteIfExists(filePath);

    } catch (IOException e) {
      throw new RuntimeException("Failed to delete file", e);
    }
  }
}
