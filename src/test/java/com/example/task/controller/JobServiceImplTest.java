package com.example.task.controller;

import com.example.task.entity.Job;
import com.example.task.service.JobService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class JobServiceImplTest {

    @Autowired
    private JobService jobService;

    @Autowired
    private MockMvc mockMvc;

    private Job mockJob;

    @Before
    public void setUp() throws Exception {
        mockJob = Job.builder()
                .jobId(1L)
                .jobName("Senior Data Analyst")
                .jobAddress("Canada; Remote; Canada; Remote; Canada; Remote;")
                .jobLogoLink("link")
                .jobOrganizationTitle("Bench")
                .jobUrlToEmployer("link")
                .jobDescription("description")
                .jobLaborFunction("IT, Data Science")
                .jobPostedDate(LocalDate.parse("2023-07-21"))
                .build();
    }

    @Test
    public void retrieveDetailedJobsInfoBy() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.post("/detailed-jobs")
                .param("function", "DataScience"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.jobList[0].jobName", is(mockJob.getJobName())));

    }
}