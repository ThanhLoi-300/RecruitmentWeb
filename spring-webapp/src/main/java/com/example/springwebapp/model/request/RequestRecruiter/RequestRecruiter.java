package com.example.springwebapp.model.request.RequestRecruiter;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRecruiter {
    private int id;
    private String companyName;
    private String companyAddress;
    private String companyDescription;
    private String fullName;
    private String linkWebsite;
    private String linkFacebook;
    private String companyPhone;
    private int accountId;
    private MultipartFile logo;

}