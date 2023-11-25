package com.example.springrestful.model.response.ResponseDashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDashboard {
    int countUsers;
    int countJobs;
    int countRecruiters;
    int countUser;
}
