package com.example.springwebapp.model.Mapper;

import com.example.springwebapp.model.response.ResponseRecruiter.ResponseRecruiter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class RecruiterMapper {
    public static ResponseRecruiter toResponseRecruiter (Object object) {
        try {
            LinkedHashMap<String, Object> recruiter = (LinkedHashMap<String, Object>) object;

            ResponseRecruiter responseRecruiter = new ResponseRecruiter();
            responseRecruiter.setId((Integer) recruiter.get("id"));
            responseRecruiter.setFullName((String) recruiter.get("fullName"));
            responseRecruiter.setCompanyName((String) recruiter.get("companyName"));
            responseRecruiter.setLinkWebsite((String) recruiter.get("linkWebsite"));
            responseRecruiter.setLinkFacebook((String) recruiter.get("linkFacebook"));
            responseRecruiter.setCompanyAddress((String) recruiter.get("companyAddress"));
            responseRecruiter.setCompanyDescription((String) recruiter.get("companyDescription"));
            responseRecruiter.setCompanyPhone((String) recruiter.get("companyPhone"));
            responseRecruiter.setAccountId((Integer) recruiter.get("accountId"));
            return responseRecruiter;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<ResponseRecruiter> toResponseRecruiterList (List<Object> objects) {
        try {
            List<ResponseRecruiter> recruiterList = new ArrayList<>();
            for (Object object : objects) {
                recruiterList.add(toResponseRecruiter(object));
            }
            return recruiterList;
        } catch (Exception e) {
            return null;
        }
    }
}
