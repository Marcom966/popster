package com.example.demo.file;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path="api/v1/file")
@CrossOrigin(origins = "http://localhost:4200")
public class filecontroller {
    @Autowired
    private final FileSystemStorageService filesService;

    public filecontroller(FileSystemStorageService filesService) {
        this.filesService = filesService;
    }



    @PostMapping
    public ResponseEntity<responseMessage> uploadFiles(@RequestParam("file") MultipartFile file, @RequestParam("userName") String user, @RequestParam("idFile") String id){
        String message = "";
        try{
            filesService.store(file, user, id);
            message = "File Uploaded successfully: "+file.getOriginalFilename();
            return  ResponseEntity.status(HttpStatus.OK).body(new responseMessage(message));
        }catch (Exception e){
            message = "Could not upload the file: "+file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new responseMessage(message));
        }
    }


    @GetMapping
    public  ResponseEntity<List<fileResponse>> getListFiles(){
        List<fileResponse> files = filesService.getAllFiles().map(dbFile->{
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(dbFile.getId()).toUriString();
            return new fileResponse(dbFile.getFileName(), dbFile.getFileSize().length, fileDownloadUri, dbFile.getType(), dbFile.getId(), dbFile.getUsernName());
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<fileToUpload> getFile(@PathVariable String id){
        fileToUpload file = filesService.getFile(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFileName()+"\"").body(file);
    }



}
