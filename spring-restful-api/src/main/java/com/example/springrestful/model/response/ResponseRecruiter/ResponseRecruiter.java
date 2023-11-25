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
public class ResponseRecruiter {
    private int id;
    private String fullName;
    private String companyName;
    private String linkWebsite;
    private String linkFacebook;
    private String companyAddress;
    private String companyDescription;
    private String companyPhone;
    private int accountId;
}
