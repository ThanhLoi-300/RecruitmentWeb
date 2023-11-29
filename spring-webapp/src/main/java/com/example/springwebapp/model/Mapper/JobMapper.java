package com.example.springwebapp.model.Mapper;

import com.example.springwebapp.model.response.ResponseJob.ResponseJob;
import com.example.springwebapp.model.response.ResponseNews.ResponseNews;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class JobMapper {
    public static ResponseJob toResponseJob (Object object) {
        try {
            LinkedHashMap<String, Object> job = (LinkedHashMap<String, Object>) object;

            ResponseJob responseJob = new ResponseJob();
            responseJob.setId((Integer) job.get("id"));
            responseJob.setTitle((String) job.get("title"));
            responseJob.setImage((String) job.get("image"));
            responseJob.setJob((String) job.get("job"));
            responseJob.setEmploymentStatus((String) job.get("employmentStatus"));
            responseJob.setCity((String) job.get("city"));
            responseJob.setRegion((String) job.get("region"));
            responseJob.setLocationDetail((String) job.get("locationDetail"));
            responseJob.setVacancy((Integer) job.get("vacancy"));
            responseJob.setDateBegin((String) job.get("dateBegin"));
            responseJob.setDateEnd((String) job.get("dateEnd"));
            responseJob.setApplicationDeadline((String) job.get("applicationDeadline"));
            responseJob.setSalary((Double) job.get("salary"));
            responseJob.setExperienceYear((Integer) job.get("experienceYear"));
            responseJob.setDescription((String) job.get("description"));
            responseJob.setResponsibility((String) job.get("responsibility"));
            responseJob.setEducation((String) job.get("education"));
            responseJob.setBenefit((String) job.get("benefit"));
            responseJob.setSkillRequired((String) job.get("skillRequired"));
            responseJob.setAccountId((Integer) job.get("accountId"));
            responseJob.setCategoryId((Integer) job.get("categoryId"));

            return responseJob;
        } catch (Exception e) {
            return null;
        }
    }
    public static List<ResponseJob> toResponseJobList (List<Object> objects) {
        try {
            List<ResponseJob> jobList = new ArrayList<>();
            for (Object object : objects) {
                jobList.add(toResponseJob(object));
            }
            return jobList;
        } catch (Exception e) {
            return null;
        }
    }
}

