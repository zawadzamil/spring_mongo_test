package com.example.spring_mongo.model;

import com.example.spring_mongo.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("blogs")
public class Blog {
    @Id
    private String id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String slug;

    private StatusEnum status = StatusEnum.UNPUBLISHED;

    private List<String> tags;

    @DBRef
    private BlogCategory category;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
