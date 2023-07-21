package com.example.task.service;

import com.example.task.entity.Job;
import com.example.task.pojo.DetailedJobReport;
import com.example.task.repository.JobRepository;
import com.example.task.util.DateConverterUtil;
import com.example.task.util.JobFunctionUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    private static final int JOBS_PER_PAGE = 20;

    private static int totalRetrievedJobs;

    @Override
    public DetailedJobReport retrieveDetailedJobsInfoBy(String jobFunction) {

        String jobFunctionUrl = JobFunctionUtil.parseLinkByJobFunction(jobFunction);

        OkHttpClient okHttpClient = new OkHttpClient();

        List<String> jobLinks = new CopyOnWriteArrayList<>();

        int efficientJobPages = calculateActivePages(okHttpClient, jobFunctionUrl);
        String urlWithPage = jobFunctionUrl + "&page=" + efficientJobPages;
        String html = fetchHtml(okHttpClient, urlWithPage);

        Document jobsDocument = Jsoup.parse(html);

        Elements vacancyElements = jobsDocument.select(".job-info");

        for (Element vacancy : vacancyElements) {
            String jobLink = vacancy.select("h4[class=sc-beqWaB kKIsob] a").attr("href");

            if (jobLink.contains("#content") && !jobLink.startsWith("http")) {
                jobLinks.add(jobLink);
            }
        }

        List<Job> jobs = jobLinks.parallelStream()
                .map(link -> ("https://jobs.techstars.com" + link))
                .map(fullLink -> parseJobDetails(okHttpClient, fullLink))
                .collect(Collectors.toList());

        jobRepository.saveAll(jobs);

        log.info("SAVED {} detailed jobs to database", jobs.size());

        return DetailedJobReport.builder()
                .jobList(jobs).build();
    }

    private Job parseJobDetails(OkHttpClient okHttpClient, String jobLink) {

        String fetchedHtml = fetchHtml(okHttpClient, jobLink);
        Document jobDocument = Jsoup.parse(fetchedHtml);

        Elements jobElements = jobDocument.select("[data-testid=content]");

        String positionName = jobDocument.select("[class=sc-beqWaB jqWDOR]").text();
        String positionUrlOrganization = jobDocument.select("div.sc-beqWaB.dqlQzp a").attr("href");;
        String positionLogoUrl = jobDocument.select("div.sc-hLseeU.lcnVyb.cBWbiv img").attr("src");;
        String organizationTitle = jobDocument.select("div.sc-beqWaB.sc-gueYoa.cxrIfE.MYFxR p").text();

        String laborFunction = "";
        String positionAddress = "";

        if (jobElements.size() >= 2) {
            laborFunction = jobElements.get(0).text();
            positionAddress = jobElements.get(1).text();
        }

        String positionPostedDate = jobDocument.select("div.sc-beqWaB.sc-gueYoa.dmdAKU.MYFxR [color=text.subtle]").text().substring(10);
        String positionDescription = jobDocument.select("[data-testid=careerPage]").text();

        return Job.builder()
                .jobName(positionName)
                .jobUrlToEmployer(positionUrlOrganization)
                .jobLogoLink(positionLogoUrl)
                .jobOrganizationTitle(organizationTitle)
                .jobLaborFunction(laborFunction)
                .jobAddress(positionAddress)
                .jobPostedDate(DateConverterUtil.convertStringToLocalDate(positionPostedDate))
                .jobDescription(positionDescription).build();

    }

    private static String fetchHtml(OkHttpClient client, String url) {

        String fetchedHtml = "";

        try {

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            fetchedHtml = response.body().string();
        } catch (IOException e) {
            e.getLocalizedMessage();
        }

        return fetchedHtml;
    }

    private static int calculateActivePages(OkHttpClient okHttpClient, String jobFunction) {

        Document counterJobsDocument = Jsoup.parse(fetchHtml(okHttpClient, jobFunction));

        totalRetrievedJobs =
                Integer.parseInt(counterJobsDocument.select("div[data-testid=header] b").text());
        return (totalRetrievedJobs % JOBS_PER_PAGE);
    }
}
