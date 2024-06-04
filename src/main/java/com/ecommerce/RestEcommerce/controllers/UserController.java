package com.ecommerce.RestEcommerce.controllers;

import com.ecommerce.RestEcommerce.entities.Session;
import com.ecommerce.RestEcommerce.entities.User;
import com.ecommerce.RestEcommerce.requests.AddUserRequest;
import com.ecommerce.RestEcommerce.requests.UpdateUserRequest;
import com.ecommerce.RestEcommerce.response.ResponseHandler;
import com.ecommerce.RestEcommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody AddUserRequest request){
        try {
            User user = service.create(request);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, user);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<Object> addImage(@RequestParam("email") String email,@RequestParam("image") MultipartFile image){
        try {
            User user = service.addImage(email,image);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, user);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        try{
            List<User> user = service.getAll();
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, user);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/session")
    public ResponseEntity<Object> getSessionByUser(@RequestParam("email") String email){
        try{
            List<Session> session = service.getSessionByUser(email);
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, session);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> get(@PathVariable("username") String username){
        try{
            User user = service.get(username);
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, user);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<Object> update(@PathVariable("username") String username, @RequestBody UpdateUserRequest request){
        try{
            User user = service.update(username,request);
            return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, user);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> delete(@PathVariable("username") String username){
        try {
            service.delete(username);
            return ResponseHandler.generateResponse("Successfully deleted data!", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
