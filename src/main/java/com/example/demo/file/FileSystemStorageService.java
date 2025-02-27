package com.example.demo.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Optional;
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

    public ResponseEntity<byte[]> getFile(String id){
        //return repo.findById(id).orElseThrow(()-> new RuntimeException("file not found with id: "+id));
        fileToUpload file = repo.findById(id).orElseThrow(()-> new RuntimeException("file non trovato"));
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-disposition", "attachment; filename"+file.getFileName());
        headers.set("content-type", file.getType());
        return new ResponseEntity<>(file.getFileSize(), headers, HttpStatus.OK);
    }

    public Stream<fileToUpload> getAllFiles(){
        return repo.findAll().stream();
    }

}

