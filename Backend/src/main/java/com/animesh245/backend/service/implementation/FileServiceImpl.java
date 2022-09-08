package com.animesh245.backend.service.implementation;

import com.animesh245.backend.config.Properties;
import com.animesh245.backend.service.definition.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
@Transactional
public class FileServiceImpl implements FileService {

    public String uploadFile(MultipartFile file)
    {
        Path rootLocation = Paths.get(Properties.WRITE_PATH);
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            Path destinationFile = rootLocation.resolve(fileName);


            if (!destinationFile.toFile().exists()) {
                destinationFile.toFile().mkdirs();
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file.getOriginalFilename();
    }
}
