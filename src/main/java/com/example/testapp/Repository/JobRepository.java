package com.example.testapp.Repository;

import com.example.testapp.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
