package com.example.springrestful.service.JobService;
import com.example.springrestful.model.entity.Job.Category;
import com.example.springrestful.model.entity.Job.Job;
import com.example.springrestful.model.request.RequestJob.RequestJob;
import com.example.springrestful.model.response.ResponseJob.ResponseJob;

import java.util.List;

public interface JobService {
    ResponseJob saveJob (RequestJob job) throws Exception;
    void removeJobById (int id) throws Exception;
    List<ResponseJob> findAllJobs () throws Exception;
    ResponseJob findJobById (int id) throws Exception;
    List<ResponseJob> findRandomSomeJobs (int limit) throws Exception;
    List<ResponseJob> findJobByCriteria (String title, String region, String category) throws Exception;
    ResponseJob editJob(RequestJob requestJob) throws Exception;
    List<ResponseJob> findJobsByType (String type) throws Exception;
    List<ResponseJob> findJobsByLocation (String locationDetail) throws Exception;
    List<ResponseJob> findJobsByCity (String city) throws Exception;
    List<ResponseJob> findJobsByRegion (String region) throws Exception;

    List<ResponseJob> findJobsByTitleLimit (String title, int limit, int offset) throws Exception;

    List<ResponseJob> findJobsBySalaryRange (double min, double max) throws Exception;
    List<ResponseJob> findJobsByCategoryId (int id) throws Exception;
    List<ResponseJob> findJobsByTop (int top) throws Exception;

    Object findAllJobLimit(int limit, int offset);

    Object plusJobViewById(int id);

}
