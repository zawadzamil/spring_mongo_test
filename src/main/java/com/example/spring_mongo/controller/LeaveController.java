package com.example.spring_mongo.controller;

import com.example.spring_mongo.dto.LeaveDTO;
import com.example.spring_mongo.enums.LeaveStatus;
import com.example.spring_mongo.model.Leave;
import com.example.spring_mongo.service.LeaveService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/leave")
public class LeaveController {
    private LeaveService leaveService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid LeaveDTO leaveDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(leaveService.create(leaveDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getList(@RequestParam(value = "id", defaultValue = "0") String id,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(leaveService.getList(id, pageRequest));
    }

    @PostMapping("/recommend")
    public ResponseEntity<?> recommend(@RequestParam(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", leaveService.action(id, LeaveStatus.RECOMMENDED)
        ));
    }

    @PostMapping("/not-recommend")
    public ResponseEntity<?> notRecommend(@RequestParam(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", leaveService.action(id, LeaveStatus.NOT_RECOMMENDED)
        ));
    }

    @PostMapping("/approve")
    public ResponseEntity<?> approve(@RequestParam(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", leaveService.action(id, LeaveStatus.APPROVED)
        ));
    }

    @PostMapping("/disapprove")
    public ResponseEntity<?> disapprove(@RequestParam(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", leaveService.action(id, LeaveStatus.DISAPPROVED)
        ));
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(@RequestParam(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", leaveService.action(id, LeaveStatus.CANCELLED)
        ));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestParam(value = "id") String id, @RequestBody Leave leave) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Leave application updated successfully",
                "data", leaveService.update(id, leave)
        ));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", leaveService.delete(id)
        ));
    }


}
