package com.example.demo.file.storage;


import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService{
    void init();

    void Store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResouce(String filename);

    void deleteAll();
    //@Autowired
    /*private static fileRepository fileRepository;

    public static fileToUpload store(MultipartFile file) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        fileToUpload toUpload = new fileToUpload(fileName, file.getContentType(), file.getBytes());
        return fileRepository.save(toUpload);
    }
    public fileToUpload getFile(String id){
        return fileRepository.findById(id).get();
    }

    public Stream<fileToUpload> getAllFiles(){
        return fileRepository.findAll().stream();
    }*/

}
