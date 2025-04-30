package com.example.testapp.Service;

import com.example.testapp.Controller.JobController;
import com.example.testapp.Controller.ScenarioController;
import com.example.testapp.Entity.Job;
import com.example.testapp.Entity.Scenario;
import com.example.testapp.Repository.JobRepository;
import com.example.testapp.Repository.ScenarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class JobScheduler {

@Autowired
    private JobRepository jobRepository;
@Autowired
    private ScenarioController scenarioController;
@Autowired
    private JobController jobController;

    @Transactional
    @Scheduled(fixedRate = 3600000)
    public void checkScheduledJobs(){
    LocalDateTime now=LocalDateTime.now();
    List<Job>jobs=jobRepository.findAll();
    for (Job job:jobs){
        String scheduledTimeString=job.getScheduledTime();
        if (scheduledTimeString == null || scheduledTimeString.isEmpty()) {
            System.out.println("Job ID " + job.getIdJob() + " has no scheduled time.");
            continue;
        }
        LocalTime scheduledTime=LocalTime.parse(scheduledTimeString);
        System.out.println("now"+ now.getHour()+":"+now.getMinute());
        System.out.println("scheduledTime.getHour()"+scheduledTime.getHour()+":"+scheduledTime.getMinute());
        if (scheduledTime.getHour()==now.getHour() && scheduledTime.getMinute()==now.getMinute()){
            System.out.println("jobId"+job.getIdJob());
            List<Scenario> scenarioList= jobController.getScenarioByProject(job.getIdJob());
            System.out.println(scenarioList);
            for (Scenario scenario:scenarioList){
                scenarioController.getSelectedSceanrio(scenario.getId());
               }
        }
    }
}
}
