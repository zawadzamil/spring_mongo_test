package com.example.spring_mongo.dto;

import com.example.spring_mongo.enums.JobNature;
import com.example.spring_mongo.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobDTO {
    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Job Type is required")
    private JobType type ;

    @NotNull(message = "Slug is required")
    private String slug;

    @NotNull(message = "Job Nature is required")
    private JobNature nature;


    private String category;

    private String description;

    private Date deadline;

    private int noOfVacancies;

    private String experience;
}
