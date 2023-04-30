package com.example.spring_mongo.controller;

import com.example.spring_mongo.dto.BlogDTO;
import com.example.spring_mongo.enums.StatusEnum;
import com.example.spring_mongo.model.Blog;
import com.example.spring_mongo.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/blogs")
@AllArgsConstructor
public class BlogController {
    private BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BlogDTO blogDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message" , "Blog created successfully.",
                "data",blogService.create(blogDTO)
        ));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestParam(value = "id", defaultValue = "0") String id,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAll(id, pageRequest));
    }

    @GetMapping("/private")
    public ResponseEntity<?> getAllPrivate(@RequestParam(value = "id", defaultValue = "0") String id,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAllPrivate(id, pageRequest));
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfo(@RequestParam("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "data",blogService.getInfo(id)
        ));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestParam("id") String id, @RequestBody Blog blog) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Blog updated successfully.",
                "data", blogService.update(id, blog)
        ));

    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete (@RequestParam("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", blogService.delete(id)
        ));
    }

    @PutMapping("/publish")
    public ResponseEntity<?> publish (@RequestParam("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", blogService.publish(id, StatusEnum.PUBLISHED)
        ));
    }

    @PutMapping("/unpublish")
    public ResponseEntity<?> unpublish (@RequestParam("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", blogService.publish(id, StatusEnum.UNPUBLISHED)
        ));
    }


}
