package com.example.task.service;

import com.example.task.entity.Job;

import java.util.List;

public interface JobService {

    List<Job> retrieveDetailedJobsInfoBy(String jobFunction);

}
