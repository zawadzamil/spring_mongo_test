package com.example.spring_mongo.controller;

import com.example.spring_mongo.dto.JobCategoryDTO;
import com.example.spring_mongo.model.JobCategory;
import com.example.spring_mongo.service.JobCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/jobs/categories")
@AllArgsConstructor
public class JobCategoryController {
    private JobCategoryService jobCategoryService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JobCategoryDTO jobCategoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Job category created successfully.",
                "data", jobCategoryService.create(jobCategoryDTO)
        ));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestParam(value = "id", defaultValue = "0") String id,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(jobCategoryService.getAll(id, pageRequest));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestParam("id") String id, @RequestBody JobCategory jobCategory){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message","Job Category updated successfully.",
                "data",jobCategoryService.update(id, jobCategory)
        ));
    }

    @DeleteMapping("/")
    public ResponseEntity<?>  delete(@RequestParam("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", jobCategoryService.delete(id)
        ));
    }
}
