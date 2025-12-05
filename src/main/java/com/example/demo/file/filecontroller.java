package com.example.demo.file;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path="api/v1/file/")
@CrossOrigin(origins = "http://localhost:4200")
public class filecontroller {
    @Autowired
    private final FileSystemStorageService filesService;

    public filecontroller(FileSystemStorageService filesService) {
        this.filesService = filesService;
    }



    @PostMapping
    public ResponseEntity<responseMessage> uploadFiles(@RequestParam("file") MultipartFile file, @RequestParam("userName") String user, @RequestParam("idFile") String id, @RequestParam("artist_name") String artistName, @RequestParam("song_name") String songName){
        String message = "";
        try{
            filesService.store(file, user, id, artistName, songName);
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
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/file/").path(dbFile.getId()).toUriString();
            return new fileResponse(dbFile.getFileName(), dbFile.getFileSize().length, fileDownloadUri, dbFile.getType(), dbFile.getId(), dbFile.getUsernName(), dbFile.getFileSize(), dbFile.getArtistName(), dbFile.getSongName());
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<fileResponse> getFileJson(@PathVariable String id) {
       ResponseEntity<byte[]> fileGeneral = filesService.getFile(id);
       fileToUpload file = filesService.getTheFile(id);
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/file/").path(file.getId()).toUriString();
        fileResponse fileRes = new fileResponse(file.getFileName(), file.getFileSize().length, fileUrl, file.getType(), file.getId(), file.getUsernName(), fileGeneral.getBody(), file.getArtistName(), file.getSongName());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"").body(fileRes);
    }


    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        String contentType;
        try {
            fileToUpload fileSecond = filesService.getTheFile(id);
            if (fileSecond == null || fileSecond.getFileSize() == null) {
                return ResponseEntity.notFound().build();
            }
            MediaType mediaType = MediaType.parseMediaType("audio/mpeg");

            return ResponseEntity.ok().contentType(mediaType).body(fileSecond.getFileSize());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<responseMessage> deleteFile(@PathVariable String id){
        String message = "";
        fileToUpload filetoDelete = filesService.getTheFile(id);
        try {
            if (filetoDelete != null) {
                filesService.delete(id);
                message = "File with id " + id + "has been successfully deleted!";
            } else {
                message = "Could not delete file with id: " + id;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new responseMessage(message));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new responseMessage(message));
        }catch (Error e){
            message = "there was an error: "+e.getMessage();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new responseMessage(message));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<fileToUpload> updateFile(@PathVariable String id, @RequestParam("artistName") String artistName, @RequestParam("songName") String songName){
        fileToUpload fileToChange = filesService.updateFile(id, artistName, songName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachments; filename=\""+fileToChange.getFileName()+"\"").body(fileToChange);

    }


}
