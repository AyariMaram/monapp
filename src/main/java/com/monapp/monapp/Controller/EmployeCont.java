package com.monapp.monapp.Controller;

import com.monapp.monapp.Model.Conge;
import com.monapp.monapp.Model.Notification;
import com.monapp.monapp.Repository.CongeRepo;
import com.monapp.monapp.Service.employeService;
import com.monapp.monapp.Service.NotService;
import com.monapp.monapp.Service.CongeTriggerService;
import com.monapp.monapp.Service.EmailService;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class EmployeCont {

    private final employeService employeService;
    private final NotService notService;
    private final EmailService emailService;
    private final CongeTriggerService congeTriggerService;
    @Autowired
    private CongeRepo congeRepo;

    private static final String UPLOAD_DIR = "src/main/resources/static/";


    @Autowired
    public EmployeCont(employeService employeService, NotService notService, EmailService emailService, CongeTriggerService congeTriggerService) {
        this.employeService = employeService;
        this.notService = notService;
        this.emailService = emailService;
        this.congeTriggerService = congeTriggerService;
    }

    @GetMapping("/afficherconges")
    public List<Conge> affichertousConges() {
        return employeService.afficherconges();
    }

    @GetMapping("/affichernotification")
    public List<Notification> affichernotifications() {
        return notService.affichernotifications();
    }

public static String uploadDirectory = System.getProperty("user.id")+"src/main/webapp/images";


    @PostMapping("/ajouterconge")
    public Conge ajouterconge(@ModelAttribute Conge conge, @RequestParam("file") MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
            Files.write(fileNameAndPath, file.getBytes());
            conge.setFile(originalFilename);
            conge = employeService.ajouterconge(conge);
            congeTriggerService.createCongeTrigger();
            return conge;
        } catch (IOException e) {
            // Gérer l'exception IO ici
            e.printStackTrace(); // ou loguer l'erreur
            // Vous pouvez retourner un message d'erreur ou une réponse appropriée si nécessaire
            return null;
        }
    }

    @PostMapping("/uploadPdf")
    @ResponseBody
    public String uploadPdfFile(@RequestParam("file") MultipartFile file, @RequestParam("id") int id) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }
        try {
            // Get the filename and build the local file path
            String filename = file.getOriginalFilename();
            String filepath = UPLOAD_DIR + filename;

            // Save the file to the static directory
            FileOutputStream fos = new FileOutputStream(filepath);
            fos.write(file.getBytes());
            fos.close();

            // Mettre à jour le chemin du fichier dans l'objet Conge
            Optional<Conge> optionalConge = congeRepo.findById(id);
            if (optionalConge.isPresent()) {
                Conge conge = optionalConge.get();
                conge.setFile(filepath);
                congeRepo.save(conge);
                return "File uploaded successfully: " + filepath;
            } else {
                return "Conge with ID " + id + " not found";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file";
        }
    }

    @GetMapping("auth/getImage/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) throws IOException {
        String imageDirectory = "src/main/resources/static/";
        Path imagePath = Paths.get(imageDirectory, imageName);

        // Read the image file into a byte array
        byte[] imageBytes = Files.readAllBytes(imagePath);

        // Determine the media type of the image
        MediaType mediaType = MediaType.IMAGE_JPEG; // Assuming JPEG format

        // Create the response entity with image bytes and appropriate headers
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(imageBytes);
    }



}

