package com.example.spring_mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobCategoryDTO {
    @NotNull(message = "Name must not be empty")
    private String name;

    @NotNull(message = "Slug is required")
    private String slug;
}
