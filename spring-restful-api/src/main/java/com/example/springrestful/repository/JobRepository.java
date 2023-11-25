package com.example.springrestful.repository;

import com.example.springrestful.model.entity.Job.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findById (int id);

    Job findBylocationDetail (String locationDetail);
    Job findByregion (String region);
    Job findBycity (String city);
    Job findBytitle (String title);

}
