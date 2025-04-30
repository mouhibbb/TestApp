package com.example.testapp.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_job")

public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idJob;
    @Column(unique = true, nullable = false)
    private String jobName;



    @Column(name ="schleduled_time")
    private String scheduledTime;

    @ElementCollection
    @CollectionTable(name = "job_scenario_ids", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "scenario_id")
    private List<Long> scenarioIds;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;


    public User getUser() {
        return user;
    }

    public Job setUser(User user) {
        this.user = user;
        return this;
    }

    public Long getIdJob() {
        return idJob;
    }

    public void setIdJob(Long idJob) {
        this.idJob = idJob;
    }

    public String getJobName() {
        return jobName;
    }

    public Job setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public List<Long> getScenarioIds() {
        return scenarioIds;
    }

    public Job setScenarioIds(List<Long> scenarioIds) {
        this.scenarioIds = scenarioIds;
        return this;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public Job setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
        return this
                ;
    }
}
