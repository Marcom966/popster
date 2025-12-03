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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service

public class FileSystemStorageService {

    @Autowired
    private storageRepo repo;

    //private final Path root = Paths.get("uploads");




    public fileToUpload store(MultipartFile file, String user, String id, String artistName, String songName) throws IOException{
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String contentType = file.getContentType();
        assert contentType != null;
        if(contentType.equals("audiofile")){
            String fileNameNew = file.getOriginalFilename();
            if(fileNameNew.contains("mp3")){
                contentType="audio/mpeg";
            } else if (fileNameNew.contains("wav")) {
                contentType="audio/wav";
            } else if (fileNameNew.contains("ogg")) {
                contentType="audio/ogg";
            }else{
                contentType="not recognized";
            }
        }
      fileToUpload fileUpload = new fileToUpload(id, user, filename, contentType, file.getBytes(), artistName, songName);
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

    public fileToUpload getTheFile(String id){
        return repo.findById(id).orElseThrow(()-> new RuntimeException("file not found with id: "+id));
    }

    public void delete (String id) {
        Optional<fileToUpload> fileToDelete = repo.findById(id);
        fileToDelete.ifPresent(deleteFile -> repo.delete(deleteFile));

    }

}

