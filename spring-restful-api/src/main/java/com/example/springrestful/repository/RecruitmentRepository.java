package com.example.springrestful.repository;

import com.example.springrestful.model.entity.Candidate.Candidate;
import com.example.springrestful.model.entity.Recruiter.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruiter, Integer> {
    Recruiter findById (int id);

}
