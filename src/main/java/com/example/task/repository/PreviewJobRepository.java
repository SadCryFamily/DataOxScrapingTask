package com.example.task.repository;

import com.example.task.entity.PreviewJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreviewJobRepository extends JpaRepository<PreviewJob, Long> {
}
