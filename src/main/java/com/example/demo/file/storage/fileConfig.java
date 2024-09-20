package com.example.demo.file.storage;

import com.example.demo.file.fileToUpload;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class fileConfig {
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
            //fileToUpload file1 = new fileToUpload("maxresdefault", "jpeg", new byte[]{23});
            //StorageService.store(file1)
        };
    }
}
