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
public class RoleDTO {
    @NotNull(message = "Alias is Required.")
    @Size(min = 3, max = 20)
    private String alias;

    @NotNull(message = "Permission String is Requires.")
    private String permissionString;
}
