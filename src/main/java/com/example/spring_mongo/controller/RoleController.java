package com.example.spring_mongo.controller;

import com.example.spring_mongo.dto.RoleDTO;
import com.example.spring_mongo.model.Role;
import com.example.spring_mongo.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.create(roleDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestParam(value = "id", defaultValue = "0") String id,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAll(pageRequest));
    }

    @GetMapping("/")
    public ResponseEntity<?> getOne(@RequestParam("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getOne(id));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestParam("id") String id, @RequestBody Role role) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.update(id, role));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", roleService.delete(id)
        ));
    }
}
