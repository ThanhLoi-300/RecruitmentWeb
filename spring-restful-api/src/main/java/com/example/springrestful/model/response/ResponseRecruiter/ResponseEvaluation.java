package com.example.springrestful.model.response.ResponseRecruiter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEvaluation {
    private int id;
    private String title;
    private String content;
    private int numberLike;
    private String dateCreated;
    private String dateUpdated;
    private String status;
    private int starNumber;
    private int recruiterId;
    private int candidateId;

}
