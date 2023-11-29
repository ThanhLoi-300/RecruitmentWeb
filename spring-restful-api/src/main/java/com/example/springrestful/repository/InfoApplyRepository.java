package com.example.springrestful.repository;

import com.example.springrestful.model.entity.Candidate.InfoApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InfoApplyRepository extends JpaRepository<InfoApply, Integer> {
    InfoApply findById(int id);
    InfoApply findByCandidateId(int candidateId);
    InfoApply findByAppliedDate(Date appliedDate);

    InfoApply findByStatus(int status);
    List<InfoApply> findByJob_IdAndJob_Recruiter_Id(Long jobId, Long recruiterId);


}
