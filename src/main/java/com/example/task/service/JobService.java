package com.example.task.service;

import com.example.task.pojo.DetailedJobReport;

public interface JobService {

    DetailedJobReport retrieveDetailedJobsInfoBy(String jobFunction);

}
