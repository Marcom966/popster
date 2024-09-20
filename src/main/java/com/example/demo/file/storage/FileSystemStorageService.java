package com.example.demo.file.storage;

import com.example.demo.file.fileToUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service

public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;
    //private final storageRepo repo;


    @Autowired
    public FileSystemStorageService(StorageProperties properties /*,storageRepo repo*/){
        if(properties.getLocation().trim().isEmpty()){
            throw new StorageException("File upload location cannot be empty.");

        }
        this.rootLocation = Paths.get(properties.getLocation());
        this.repo = repo;
    }

    @Override
    public void store(MultipartFile file /*, fileToUpload fiLe*/){
        try{
            if(file.isEmpty()){
                throw new StorageException("Failed to store empty file");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();
            if(!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())){
                throw new StorageException("Cannot store file outside current directory");
            }
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            //repo.save(/*fiLe*/);
        }catch (IOException e){
            throw new StorageException("failed to store file", e);
        }
    }

    @Override
    public Stream<Path> loadAll(){
        try{
            return Files.walk(this.rootLocation,1).filter(path -> !path.equals(this.rootLocation)).map(this.rootLocation::relativize);
        }catch (IOException e){
            throw new StorageException("Failed to store files", e);
        }
    }

    @Override
    public Path load(String filename){
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String fileName){
        try{
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()||resource.isReadable()){
                return resource;
            }
            else {
                throw new StorageNotFoundException("Could not read file:" + fileName);
            }
        }catch (MalformedURLException e){
            throw new StorageNotFoundException("Could not read file"+fileName, e);
        }

    }

    @Override
    public void deleteAll(){
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init(){
        try{
            Files.createDirectories(rootLocation);
        }catch (IOException e){
            throw new StorageException("could not initialize storage", e);
        }
    }
}

