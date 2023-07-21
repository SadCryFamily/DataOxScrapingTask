package com.example.task.entity;

import com.example.task.entity.converter.ListToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Table(name = "preview_job")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreviewJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_job_id")
    private Long previewJobId;

    @Column(name = "p_job_title")
    private String previewJobTitle;

    @Column(name = "p_job_employer")
    private String previewJobEmployer;

    @Column(name = "p_job_address")
    private String previewJobAddress;

    @Column(name = "p_job_posted_time")
    private LocalDate previewJobPostedTime;

    @Column(name = "p_job_tags", columnDefinition = "text []")
    @Convert(converter = ListToStringConverter.class)
    private List<String> previewJobTags;



}
