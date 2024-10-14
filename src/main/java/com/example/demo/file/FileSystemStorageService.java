package com.example.demo.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service

public class FileSystemStorageService {

    @Autowired
    private storageRepo repo;


    public fileToUpload store(MultipartFile file /*, fileToUpload fiLe*/) throws IOException{
      String filename = StringUtils.cleanPath(file.getOriginalFilename());
      fileToUpload fileUpload = new fileToUpload(filename, file.getContentType(), file.getBytes());
      return repo.save(fileUpload);
    }

    public fileToUpload getFile(String id){
        return repo.findById(id).get();
    }

    public Stream<fileToUpload> getAllFiles(){
        return repo.findAll().stream();
    }
}

