package com.monapp.monapp.Service;

import org.springframework.stereotype.Service;

@Service
public class ImageService {







    /*
    public String saveImageToStorage(String uploadDirectory, MultipartFile imageFile ) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

        Path uploadPath = Path.of(uploadDirectory);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
    }*/
}
