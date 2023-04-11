package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Controller;

import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Repo.IdProveRepo;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.AccountCreation;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.IdProve;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.User;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.service.AccountCreationService;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    @Autowired
    private AccountCreationService accountCreationService;
    @Autowired
    private UserService userService;
    @Autowired
    private IdProveRepo idProveRepo;
    @PostMapping("/saveDetails")
    public ResponseEntity<Map<String,Object>> createUser(@RequestBody User user) {
        System.out.println(user);
        User createdUser = userService.addUser(user);
        if (createdUser != null){
            return ResponseEntity.ok(Collections.singletonMap("message", "Data saved"));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("message","error while saving data"));
    }

    @GetMapping("/userDetails")
    public ResponseEntity<Map<String,Object>> getUser(@RequestParam("id") String id){
        User user = userService.getUser(Long.parseLong(id));
        if(user != null){
            System.out.println(user);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("message","available");
            map.put("user",user);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }else{
            System.out.println("not found");
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message","not found"));
        }
    }

    @GetMapping("/getUserData")
    public ResponseEntity<Map<String,Object>> getUserData(@RequestParam("id") String id){
        AccountCreation accountCreation = accountCreationService.getAccountData(Long.parseLong(id));
        User user = userService.getUser(Long.parseLong(id));
        if(user != null && accountCreation!=null){
            System.out.println(user);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("message","available");
            map.put("user",user);
            map.put("account",accountCreation);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }else{
            System.out.println("not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message","not found"));
        }
    }

    @PostMapping("/IdProveUpload")
    public ResponseEntity<Map<String,Object>> createEntity(
            @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message","file should be PNG or JPEG format"));
        }
        long IdSerialNumber = userService.saveIdProve(file);
        if(IdSerialNumber==0){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message","Error while uploading file"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("id",IdSerialNumber));
    }

    @GetMapping("/idProve")
    public ResponseEntity<Map<String,Object>> getIdProve(
            @RequestParam("idSerialNo") long idSerialNo) {
        IdProve idProve = userService.getIdProve(idSerialNo);
        if (idProve == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "No File available"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("IdProve", idProve));
    }
    @GetMapping("/file")
    public ResponseEntity<byte[]> getFile(@RequestParam("idSerialNo") long idSerialNo) {
        IdProve idProve = userService.getIdProve(idSerialNo);
        // Load the file into a byte array
        byte[] fileBytes = idProve.getFileData();// load the file into a byte array

        // Set the content type of the response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        if(idProve.getFileType().equals("image/jpeg"))
        headers.setContentDispositionFormData("attachment", idSerialNo+".jpg");
        else
            headers.setContentDispositionFormData("attachment", idSerialNo+".png");

        // Return the response entity with the file bytes and headers
        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }
}