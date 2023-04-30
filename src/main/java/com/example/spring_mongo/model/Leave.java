package com.example.spring_mongo.model;

import com.example.spring_mongo.enums.LeaveNature;
import com.example.spring_mongo.enums.LeaveStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("leaves")
public class Leave {
    @Id
    private String id;

    @NotNull(message = "Leave Nature is required")
    private LeaveNature natureOfLeave;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    @Size(max = 500, message = "Reason up to 500 characters")
    private String reason;

    @NotNull
    private Date dateOfJoining;

    private Date dateOfActualJoining;

    private int totalLeaveAvailed;

    @NotNull
    private LeaveStatus status = LeaveStatus.PENDING;

    @DBRef
    private User user;

}
