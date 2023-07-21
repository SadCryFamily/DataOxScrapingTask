package com.example.task.controller;

import com.example.task.pojo.DetailedJobReport;
import com.example.task.pojo.PreviewJobReport;
import com.example.task.service.JobService;
import com.example.task.service.PreviewJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @Autowired
    private PreviewJobService previewJobService;

    @Autowired
    private JobService jobService;

    @GetMapping("/detailed-jobs")
    public DetailedJobReport retrieveDetailedJobsInfoBy(@RequestParam("function") String jobFunction) {
        return jobService.retrieveDetailedJobsInfoBy(jobFunction);
    }

    @GetMapping("/minimized-jobs")
    public PreviewJobReport retrieveMinimizedJobsInfoBy(@RequestParam("function") String jobFunction) {
        return previewJobService.retrieveMinimizedJobsInfoBy(jobFunction);
    }

}
