package com.example.springrestful.controller;

import com.example.springrestful.model.entity.Candidate.InfoApply;
import com.example.springrestful.service.CandidateService.InfoApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/infoapply")
public class InfoApplyController {

    private final InfoApplyService infoApplyService;

    @Autowired
    public InfoApplyController(InfoApplyService infoApplyService) {
        this.infoApplyService = infoApplyService;
    }

    @GetMapping("/candidatesApplied")
    public List<InfoApply> getCandidatesAppliedToJobByRecruiter(
            @RequestParam Long jobId,
            @RequestParam Long recruiterId) throws Exception {
        return infoApplyService.getCandidatesAppliedToJobByRecruiter(jobId, recruiterId);
    }
}
