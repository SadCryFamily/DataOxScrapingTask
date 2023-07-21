package com.example.task.service;

import com.example.task.pojo.PreviewJobReport;

public interface PreviewJobService {

    PreviewJobReport retrieveMinimizedJobsInfoBy(String jobFunction);

}
