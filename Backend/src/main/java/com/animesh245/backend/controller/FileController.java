package com.animesh245.backend.controller;

import com.animesh245.backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @Autowired
    FileService fileService;
    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file)
    {
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getName());
//        System.out.println(file.getSize() +" bytes");
//        System.out.println(file.getContentType());

        try {
            //Condition/ validation
            if (file.isEmpty()) {
                return ResponseEntity.internalServerError().body("Request must contain file");
            }

            //File Upload Code

            boolean f = fileService.uploadFile(file);

            if(f)
            {
                return ResponseEntity.ok("File uploaded successfully");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return ResponseEntity.internalServerError().body("Try Again!");
    }
}
