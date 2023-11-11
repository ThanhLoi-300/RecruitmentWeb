package com.example.springwebapp.model.response.ResponseAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAccount {
    private int id;
    private String username;
    private String role;
    private int status;
    private String email;
    private String phoneNumber;
    private String fullName;
    private String address;
    private String education;
    private String description;
    private String avatarImg;
    private String identifyCardNumber;
    private String identifyCardName;
    private String birthday;
    private int followers;
    private int following;
}
