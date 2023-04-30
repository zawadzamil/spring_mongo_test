package com.example.spring_mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    @NotNull(message = "Name is required.")
    private String name;

    @NotNull(message = "Username is required")
    @Size(max = 30, message = "Username must between {min} to {max} characters")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 character long.")
    private String password;

    private String profileImage;
}
