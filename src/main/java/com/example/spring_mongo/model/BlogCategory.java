package com.example.spring_mongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("blogCategories")
public class BlogCategory {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String slug;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
