package com.example.task.util;

import com.example.task.enums.JobFunction;

import java.util.Arrays;
import java.util.Optional;

public class JobFunctionUtil {

    public static String parseLinkByJobFunction(String jobFunction) {
        Optional<String> jobLinkOptional = Arrays.stream(JobFunction.values())
                .filter(job -> job.getJobName().equalsIgnoreCase(jobFunction))
                .map(JobFunction::getJobLink)
                .findFirst();

        return jobLinkOptional.orElse(null);
    }

}
