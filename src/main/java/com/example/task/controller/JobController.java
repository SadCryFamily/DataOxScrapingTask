package com.example.task.controller;

import com.example.task.entity.Job;
import com.example.task.pojo.PreviewJobReport;
import com.example.task.service.PreviewJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {

    @Autowired
    private PreviewJobService previewJobService;

    @PostMapping("/detailed-jobs")
    public List<Job> retrieveDetailedJobsInfoBy(@RequestParam("function") String jobFunction) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/minimized-jobs")
    public PreviewJobReport retrieveMinimizedJobsInfoBy(@RequestParam("function") String jobFunction) {
        return previewJobService.retrieveMinimizedJobsInfoBy(jobFunction);
    }

}
