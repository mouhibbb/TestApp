package com.example.testapp.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class JobRequestDTO {
    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    private String scheduledTime;

    List<Long> ScenarioIds;

    private String jobCreater;
    private String jobName;
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;

    }



    public List<Long> getScenarioIds() {
        return ScenarioIds;
    }

    public void setScenarioIds(List<Long> scenarioIds) {
        ScenarioIds = scenarioIds;
    }
    public String getJobCreater() {
        return jobCreater;
    }

    public void setJobCreater(String jobCreater) {
        this.jobCreater = jobCreater;
    }

}
