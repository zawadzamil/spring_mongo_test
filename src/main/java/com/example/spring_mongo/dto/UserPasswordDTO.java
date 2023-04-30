package com.example.spring_mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPasswordDTO {
    @NotNull
    private String oldPassword;

    @NotNull
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String newPassword;
}
