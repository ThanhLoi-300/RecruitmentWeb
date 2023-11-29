package com.example.springwebapp.Service.RecruiterService;

import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseRecruiter.ResponseRecruiter;

public interface RecruiterService {
    ApiResponse<ResponseRecruiter> getRecruiterById (int id);

}
