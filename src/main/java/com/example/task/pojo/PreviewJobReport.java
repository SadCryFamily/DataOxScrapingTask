package com.example.task.pojo;

import com.example.task.entity.PreviewJob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class PreviewJobReport {

    private int totalJobsCounter;

    private List<PreviewJob> previewJobList;

}
