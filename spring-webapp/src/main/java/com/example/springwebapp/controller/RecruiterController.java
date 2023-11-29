package com.example.springwebapp.controller;

import com.example.springwebapp.Service.RecruiterService.RecruiterService;
import com.example.springwebapp.model.Mapper.RecruiterMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class RecruiterController {
    @Autowired
    RecruiterService recruiterService;

    @GetMapping(value = "/recruiter_listings")
    public String getRecruiterListView () {
        return "recruiter_listings";
    }
    @GetMapping(value = "/post_recruiter")
    public String getPostJobView () {
        return "post_recruiter";
    }
}
