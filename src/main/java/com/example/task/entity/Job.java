package com.example.task.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "job")
@Builder
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "job_url_to_employer")
    private String jobUrlToEmployer;

    @Column(name = "job_logo_link")
    private String jobLogoLink;

    @Column(name = "job_organization_title")
    private String jobOrganizationTitle;

    @Column(name = "job_labor_function")
    private String jobLaborFunction;

    @Column(name = "job_address")
    private String jobAddress;

    @Column(name = "job_posted_date")
    private LocalDate jobPostedDate;

    @Column(name = "job_description")
    private String jobDescription;

    public static LocalDate convertStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        return LocalDate.parse(date, formatter);
    }

}
