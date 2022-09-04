package com.animesh245.backend.service.definition;

import org.springframework.web.multipart.MultipartFile;

public interface FileService
{
    boolean uploadFile(MultipartFile file);
}
