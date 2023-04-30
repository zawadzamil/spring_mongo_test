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
@Document("jobCategories")
public class JobCategory {
    @Id
    private String id;


    @NotNull(message = "Name must not be empty")
    private String name;

    @NotNull(message = "Slug is required")
    private String slug;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setSlug(String slug) {
        this.slug = slug.toLowerCase().trim();
    }
}
