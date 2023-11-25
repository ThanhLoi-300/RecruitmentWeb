package com.example.springrestful.model.mapper;

import com.example.springrestful.model.entity.Recruiter.Recruiter;
import com.example.springrestful.model.response.ResponseCandidate.ResponseCandidate;
import com.example.springrestful.model.response.ResponseRecruiter.ResponseRecruiter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RecruiterMapper {
    @Mapping(source = "recruiter.id", target = "id")
    @Mapping(source = "recruiter.fullName", target = "fullName")
    @Mapping(source = "recruiter.companyName", target = "companyName")
    @Mapping(source = "recruiter.linkWebsite", target = "linkWebsite")
    @Mapping(source = "recruiter.linkFacebook", target = "linkFacebook")
    @Mapping(source = "recruiter.companyAddress", target = "companyDescription")
    @Mapping(source = "recruiter.companyPhone", target = "companyPhone")
    @Mapping(source = "recruiter.account.id", target = "accountId")
    ResponseRecruiter toResponseRecruiter(Recruiter recruiter);
    List<ResponseRecruiter> toResponseRecruiterList(List<Recruiter> recruiter);

}
