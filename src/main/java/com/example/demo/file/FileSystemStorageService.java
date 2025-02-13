package com.example.demo.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.stream.Stream;

@Service

public class FileSystemStorageService {

    @Autowired
    private storageRepo repo;


    public fileToUpload store(MultipartFile file, String user, String id) throws IOException{
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
      fileToUpload fileUpload = new fileToUpload(id, user, filename, file.getContentType(), file.getBytes());
      return repo.save(fileUpload);
    }

    public fileToUpload getFile(String id){
        return repo.findById(id).get();
    }

    public Stream<fileToUpload> getAllFiles(){
        return repo.findAll().stream();
    }

}

