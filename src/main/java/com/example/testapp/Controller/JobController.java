package com.example.testapp.Controller;

import com.example.testapp.DTO.JobRequestDTO;
import com.example.testapp.Entity.Job;
import com.example.testapp.Entity.Project;
import com.example.testapp.Entity.Scenario;
import com.example.testapp.Entity.User;
import com.example.testapp.Repository.JobRepository;
import com.example.testapp.Repository.ProjectRepository;
import com.example.testapp.Repository.ScenarioRepository;
import com.example.testapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/job")
public class JobController {
    @Autowired JobRepository jobRepository;
    @Autowired UserRepository userRepository;
    @Autowired
    private ScenarioRepository scenarioRepository;

    @PostMapping
    public ResponseEntity<Long> saveJob(@RequestBody JobRequestDTO jobRequest) {
        System.out.println("time"+jobRequest.getScheduledTime());
        User user=userRepository.findByEmail(jobRequest.getJobCreater());
        Job job= new Job().setJobName(jobRequest.getJobName()).setUser(user).setScenarioIds(jobRequest.getScenarioIds()).setScheduledTime(jobRequest.getScheduledTime());
        try {
            Job savedJob = jobRepository.save(job);
            return ResponseEntity.ok(savedJob.getIdJob());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping
    public Set<Job> getJobsByUser(@RequestParam  String email){
        System.out.println(email);
        User user=userRepository.findByEmail(email);
        System.out.println(user);
        if (user!=null){return user.getJobs();}
        else{throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
    @GetMapping("/{id}")
    public List<Scenario> getScenarioByProject(@PathVariable Long id) {
        System.out.println("mouhib "+ id);
        Optional<Job> jobOptional = jobRepository.findById(id);
        System.out.println(jobOptional);
        if (jobOptional.isEmpty()) {
            return Collections.emptyList(); // ou retourne une 404 avec ResponseEntity
        }

        Job job = jobOptional.get();
        List<Long> idScenarioList = job.getScenarioIds();
        System.out.println("idScenarioList"+ idScenarioList);
        List<Scenario> scenarios = new ArrayList<>();

        for (Long idScenario : idScenarioList) {
            scenarioRepository.findById(idScenario).ifPresent(scenarios::add);
        }

        return scenarios;
    }



}
