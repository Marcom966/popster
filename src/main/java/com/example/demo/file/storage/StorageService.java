package com.example.demo.file.storage;


import com.example.demo.file.fileToUpload;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;


public interface StorageService {
    void init();

    public void store(MultipartFile file /*,fileToUpload fiLe*/);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();


}
