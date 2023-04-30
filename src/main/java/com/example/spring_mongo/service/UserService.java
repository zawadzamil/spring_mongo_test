package com.example.spring_mongo.service;

import com.example.spring_mongo.dto.UserDTO;
import com.example.spring_mongo.dto.UserPasswordDTO;
import com.example.spring_mongo.exceptions.ResourceAlreadyExistException;
import com.example.spring_mongo.exceptions.ResourceNotFoundException;
import com.example.spring_mongo.model.User;
import com.example.spring_mongo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User crate(UserDTO userDTO) {
        User existingUser = userRepository.findByUsername(userDTO.getUsername());
        if (existingUser != null) {
            throw new ResourceAlreadyExistException("User already exists");
        }

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public Page<User> getAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    public User update(String id, User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && !existingUser.getId().equals(id)) {
            throw new ResourceAlreadyExistException("User already exists");
        }

        return userRepository.findById(id).map(item -> {
            item.setName(user.getName());
            item.setProfileImage(user.getProfileImage());
            item.setUsername(user.getUsername());
            item.setUpdatedAt(LocalDateTime.now());

            return userRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    public User updatePassword(String id, UserPasswordDTO userPasswordDTO) {

        User oldUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found."));
        if (!oldUser.getPassword().equals(userPasswordDTO.getOldPassword())) {
            throw new BadCredentialsException("Password didn't match");
        }

        return userRepository.findById(id).map(item -> {
            item.setPassword(userPasswordDTO.getNewPassword());

            return userRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    public String delete(String id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found."));
        userRepository.deleteById(id);
        return "User deleted successfully.";
    }
}
