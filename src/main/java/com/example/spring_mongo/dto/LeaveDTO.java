package com.example.spring_mongo.dto;

import com.example.spring_mongo.enums.LeaveNature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeaveDTO {
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
}
