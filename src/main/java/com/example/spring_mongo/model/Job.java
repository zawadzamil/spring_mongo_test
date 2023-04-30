package com.example.spring_mongo.model;

import com.example.spring_mongo.enums.JobNature;
import com.example.spring_mongo.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("jobs")
public class Job {
    @Id
    private String id;

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Job Type is required")
    private JobType type ;

    @NotNull(message = "Slug is required")
    private String slug;

    @NotNull(message = "Job Nature is required")
    private JobNature nature;

    @DBRef
    private JobCategory category;

    private String description;

    private Date deadline;

    private int noOfVacancies;

    private String experience;

    private boolean active = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
