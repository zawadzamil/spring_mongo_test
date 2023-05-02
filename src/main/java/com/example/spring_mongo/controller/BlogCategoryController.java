package com.example.spring_mongo.controller;

import com.example.spring_mongo.dto.BlogCategoryDTO;
import com.example.spring_mongo.model.BlogCategory;
import com.example.spring_mongo.service.BlogCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/blogs/categories")
@AllArgsConstructor
public class BlogCategoryController {
    private BlogCategoryService blogCategoryService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid BlogCategoryDTO blogCategoryDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(blogCategoryService.create(blogCategoryDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestParam(value = "id", defaultValue = "0") String id,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(blogCategoryService.getAll(id, pageRequest));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(String id, @RequestBody @Valid BlogCategory blogCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(blogCategoryService.update(id, blogCategory));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", blogCategoryService.delete(id)
        ));
    }

}
