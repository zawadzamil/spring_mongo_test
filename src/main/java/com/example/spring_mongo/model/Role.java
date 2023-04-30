package com.example.spring_mongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Role {
    @Id
    private String id;

    @NotNull()
    @Size(min = 3, max = 20)
    private String alias;

    @NotNull()
    private String permissionString;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setAlias(String alias) {
        this.alias = alias.trim();
    }
}
