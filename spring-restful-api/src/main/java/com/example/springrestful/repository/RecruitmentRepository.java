package com.example.springrestful.repository;

import org.springframework.stereotype.Repository;

@Repository

public interface RecruitmentRepository {

public interface RecruitmentRepository extends JpaRepository<Recruiter, Integer> {
    Recruiter findById (int id);
    //Candidate contactWithCandidate(String email);

}
