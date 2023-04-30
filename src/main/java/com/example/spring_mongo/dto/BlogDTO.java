package com.example.spring_mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogDTO {
    @NotNull(message = "Title is required.")
    private String title;

    @NotNull(message = "Content is required.")
    private String content;

    @NotNull(message = "Slug is required.")
    private String slug;

    private List<String> tags;

    private String category;
}
