package com.example.springwebapp.model.response.ResponseRecruiter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
