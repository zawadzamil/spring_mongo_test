package com.example.spring_mongo.controller;

import com.example.spring_mongo.dto.JobDTO;
import com.example.spring_mongo.model.Job;
import com.example.spring_mongo.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/jobs")
@AllArgsConstructor
public class JobController {
    private JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody JobDTO jobDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Job created successfully",
                "data", jobService.create(jobDTO)
        ));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestParam(value = "id", defaultValue = "0")String id,
                                    @RequestParam(value = "page", defaultValue ="0")int page,
                                    @RequestParam(value = "size", defaultValue = "10")int size){
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(jobService.getAll(id, pageRequest,false));
    }

    @GetMapping("/private")
    public ResponseEntity<?> getAllPrivate(@RequestParam(value = "id", defaultValue = "0")String id,
                                           @RequestParam(value = "page", defaultValue ="0")int page,
                                           @RequestParam(value = "size", defaultValue = "10")int size){
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(jobService.getAll(id, pageRequest,true));
    }

    @PutMapping("/")
    public ResponseEntity<?> update (@RequestParam("id") String id, @RequestBody() Job job){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Job updated successfully.",
                "data", jobService.update(id, job)
        ));

    }

    @PutMapping("/active")
    public ResponseEntity<?> activate(@RequestParam("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", jobService.activation(id, true)
        ));
    }

    @PutMapping("/deactive")
    public ResponseEntity<?> deactivate(@RequestParam("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", jobService.activation(id, false)
        ));
    }


}
