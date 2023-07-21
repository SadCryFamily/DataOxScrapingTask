package com.example.task.controller;

import com.example.task.entity.PreviewJob;
import com.example.task.service.PreviewJobService;
import org.hamcrest.Matchers;
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
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class PreviewJobServiceImplTest {

    @Autowired
    private PreviewJobService previewJobService;

    @Autowired
    private MockMvc mockMvc;

    private PreviewJob mockPreviewJob;

    @Before
    public void setUp() throws Exception {
        mockPreviewJob = PreviewJob.builder()
                .previewJobId(1L)
                .previewJobTitle("Senior Data Analyst")
                .previewJobAddress("Canada; Remote; Canada; Remote; Canada; Remote ·")
                .previewJobEmployer("Bench")
                .previewJobPostedTime(LocalDate.parse("2023-07-21"))
                .previewJobTags(List.of("Finance", "Software", "201 - 1000", "Series C+")).build();
    }

    @Test
    public void retrieveMinimizedJobsInfoBy() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.post("/minimized-jobs")
                .param("function", "DataScience"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalJobsCounter", Matchers.is(197)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.previewJobList[0].previewJobTitle", Matchers.is(mockPreviewJob.getPreviewJobTitle())));

    }
}