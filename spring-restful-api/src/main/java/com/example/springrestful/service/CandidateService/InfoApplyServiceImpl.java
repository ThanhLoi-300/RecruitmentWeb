package com.example.springrestful.service.CandidateService;

import com.example.springrestful.model.entity.Candidate.InfoApply;
import com.example.springrestful.repository.InfoApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InfoApplyServiceImpl implements InfoApplyService {

    private final InfoApplyRepository infoApplyRepository;

    @Autowired
    public InfoApplyServiceImpl(InfoApplyRepository infoApplyRepository) {
        this.infoApplyRepository = infoApplyRepository;
    }

    @Override
    public List<InfoApply> getCandidatesAppliedToJobByRecruiter(Long jobId, Long recruiterId) {
        return infoApplyRepository.findByJob_IdAndJob_Recruiter_Id(jobId, recruiterId);
    }
}