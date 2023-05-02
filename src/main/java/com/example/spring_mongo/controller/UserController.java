package com.example.spring_mongo.controller;

import com.example.spring_mongo.dto.UserDTO;
import com.example.spring_mongo.dto.UserPasswordDTO;
import com.example.spring_mongo.model.User;
import com.example.spring_mongo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.crate(userDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll(pageRequest));
    }

    @PutMapping("/update/profile")
    public ResponseEntity<?> updateProfile(@RequestParam("id") String id, @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, user));
    }

    @PutMapping("/update/password")
    public ResponseEntity<?> updatePassword(@RequestParam("id") String id, @RequestBody UserPasswordDTO userPasswordDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updatePassword(id, userPasswordDTO));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", userService.delete(id)
        ));
    }
}
