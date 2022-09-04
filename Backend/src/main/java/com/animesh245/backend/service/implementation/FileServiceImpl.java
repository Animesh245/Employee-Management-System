package com.animesh245.backend.service.implementation;

import com.animesh245.backend.config.Properties;
import com.animesh245.backend.service.definition.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
@Transactional
public class FileServiceImpl implements FileService {

    public boolean uploadFile(MultipartFile file)
    {
        boolean status = false;

        try {

//            //IF using only Stream Api
//            //read
//            InputStream is = file.getInputStream();
//            byte [] data = new byte[is.available()];
//            is.read(data);
//
//            //write
//            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+ File.separator + file.getOriginalFilename());
//            fos.write(data);
//
//            fos.flush();
//            fos.close();

            //If using NIO package and InputStream
            Files.copy(file.getInputStream(), Paths.get(Properties.WRITE_PATH +File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            status=true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }
}
