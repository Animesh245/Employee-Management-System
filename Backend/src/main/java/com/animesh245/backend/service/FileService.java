package com.animesh245.backend.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


@Service
public class FileService{

    public final String UPLOAD_DIR = new ClassPathResource("static/files/").getFile().getAbsolutePath();

    public FileService() throws IOException {
    }

    public boolean uploadFile(MultipartFile file)
    {
        boolean status = false;

        try {

            //IF using Stream Api

            //read
            InputStream is = file.getInputStream();
            byte [] data = new byte[is.available()];
            is.read(data);

            //write
            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+ File.separator + file.getOriginalFilename());
            fos.write(data);

            fos.flush();
            fos.close();

            status=true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }
}