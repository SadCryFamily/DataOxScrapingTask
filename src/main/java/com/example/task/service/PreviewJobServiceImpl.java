package com.example.task.service;

import com.example.task.entity.PreviewJob;
import com.example.task.pojo.PreviewJobReport;
import com.example.task.repository.PreviewJobRepository;
import com.example.task.util.DateConverterUtil;
import com.example.task.util.JobFunctionUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.task.util.ConnectionUtil.fetchHtml;

@Service
@Slf4j
public class PreviewJobServiceImpl implements PreviewJobService {

    @Autowired
    private PreviewJobRepository previewJobRepository;

    private static final int JOBS_PER_PAGE = 20;

    private static int totalRetrievedJobs;

    @Override
    public PreviewJobReport retrieveMinimizedJobsInfoBy(String jobFunction) {

        String jobFunctionUrl = JobFunctionUtil.parseLinkByJobFunction(jobFunction);

        OkHttpClient okHttpClient = new OkHttpClient();

        int efficientJobPages = parseTotalJobValue(okHttpClient, jobFunctionUrl);
        String html = fetchHtml(okHttpClient, jobFunctionUrl);

        Document jobsDocument = Jsoup.parse(html);

        Elements vacancyElements = jobsDocument.select(".job-info");

        List<PreviewJob> previewJobList = new ArrayList<>();

        for (Element vacancyElement : vacancyElements) {

            String jobTitle = vacancyElement.select("[itemprop=title]").text();
            String jobEmployer = vacancyElement.select("[itemprop=hiringOrganization]").text();
            String jobAddress = vacancyElement.select("[itemprop=jobLocation]").text();
            String jobDatePosted = vacancyElement.select("div [itemprop=datePosted]").attr("content");

            Elements tagElements = vacancyElement.select("div[data-testid=tag]");

            List<String> tagNames = new ArrayList<>();

            for (Element tagElement : tagElements) {
                String tagName = tagElement.text();
                tagNames.add(tagName);
            }

            PreviewJob previewJob = PreviewJob.builder()
                    .previewJobTitle(jobTitle)
                    .previewJobEmployer(jobEmployer)
                    .previewJobAddress(jobAddress)
                    .previewJobPostedTime(DateConverterUtil.convertSimpleStringToLocalDate(jobDatePosted))
                    .previewJobTags(tagNames).build();

            previewJobList.add(previewJob);
        }

        log.info("SAVED {} detailed jobs to database", previewJobList.size());

        previewJobRepository.saveAll(previewJobList);

        return PreviewJobReport.builder()
                .totalJobsCounter(totalRetrievedJobs)
                .previewJobList(previewJobList)
                .build();

    }

    private static int parseTotalJobValue(OkHttpClient okHttpClient, String jobFunction) {
        Document counterJobsDocument = Jsoup.parse(fetchHtml(okHttpClient, jobFunction));
        totalRetrievedJobs = Integer.parseInt(counterJobsDocument.select("div[data-testid=header] b").text());

        return totalRetrievedJobs;
    }
}
