package com.example.demo.file;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.file.storage.StorageNotFoundException;
import com.example.demo.file.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path="api/v1/file")
@CrossOrigin(origins = "http://localhost:4200")
public class filecontroller {
    @Autowired
    private final StorageService filesService;

    public filecontroller(StorageService filesService) {
        this.filesService = filesService;
    }



    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException{
      model.addAttribute("files", filesService.loadAll().map(path -> MvcUriComponentsBuilder.fromMethodName(filecontroller.class, "serveFile", path.getFileName().toString()).build().toUri().toString()).collect(Collectors.toList()));
      return model.toString();
    };


    @GetMapping("/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
        Resource file = filesService.loadAsResource(filename);
        if(file==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"").body(file);
    }


    /*
    @PostMapping("")
    public String hadleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        filesService.store(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded"+file.getOriginalFilename()+"!");
        return "redirect:/";
    }
    */

    @ExceptionHandler(StorageNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageNotFoundException exc){
        return ResponseEntity.notFound().build();
    }

    /*public ResponseEntity<List<fileResponse>> uploadFile(@RequestParam("file") MultipartFile file){
        List<fileResponse> files = filesService.loadAll().map(dbFile ->{
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(dbFile.getId()).toUriString();
            return new fileResponse(dbFile.getFileName(), dbFile.getFileSize().length, dbFile.getType(), fileDownloadUri);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    */
    @PostMapping("/")
    public ResponseEntity<responseMessage> uploadFiles(@RequestParam MultipartFile file /*,@RequestBody fileToUpload fiLe*/){
        String message = "";
        try{
            //filesService.store(file /*,fiLe*/);
            message = "File Uploaded successfully: "+file.getOriginalFilename();
            return  ResponseEntity.status(HttpStatus.OK).body(new responseMessage(message));
        }catch (Exception e){
            message = "Could not upload the file: "+file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new responseMessage(message));
        }
    }

    /*
    @GetMapping("/files/{id}")
    public  ResponseEntity<byte[]> getFile(@PathVariable String id){
        fileToUpload file = filesService.getFile(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFileName()+"\"").body(file.getFileSize());
    }

     */

}
