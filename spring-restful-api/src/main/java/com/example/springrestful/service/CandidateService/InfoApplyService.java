package com.example.springrestful.service.CandidateService;

import com.example.springrestful.model.entity.Candidate.InfoApply;
import com.example.springrestful.repository.InfoApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface InfoApplyService {
    List<InfoApply> getCandidatesAppliedToJobByRecruiter(Long jobId, Long recruiterId)throws Exception;

}
