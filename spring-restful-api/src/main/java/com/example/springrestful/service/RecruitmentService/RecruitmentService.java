package com.example.springrestful.service.RecruitmentService;

import com.example.springrestful.model.request.RequestRecruiter.RequestRecruiter;
import com.example.springrestful.model.response.ResponseRecruiter.ResponseRecruiter;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecruitmentService {
    ResponseRecruiter saveRecruiter (RequestRecruiter requestRecruiter) throws Exception;
    ResponseRecruiter editRecruiter (RequestRecruiter requestRecruiter) throws Exception;

    void removeRecruiterById (int id) throws Exception;
    List<ResponseRecruiter> findAllRecruiters () throws Exception;
    ResponseRecruiter findRecruiterById (int id) throws Exception;

    void contactWithCandidate(String to, String subject, String content) throws MessagingException;

    List<ResponseRecruiter> followCandidate (int infoApplyStatus) throws Exception;

    String writeImg(MultipartFile file);
}
