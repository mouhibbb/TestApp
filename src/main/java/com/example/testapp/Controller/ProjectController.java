package com.example.testapp.Controller;

import com.example.testapp.DTO.ProjectRequestDTO;
import com.example.testapp.Entity.Project;
import com.example.testapp.Entity.Scenario;
import com.example.testapp.Entity.User;
import com.example.testapp.Repository.ProjectRepository;
import com.example.testapp.Repository.ScenarioRepository;
import com.example.testapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScenarioRepository scenarioRepository;

    @PostMapping()
    public Long saveProject(@RequestBody ProjectRequestDTO project) {

        User user=userRepository.findByEmail(project.getEmail());
        System.out.println(user);
        Project project1=new Project();
        project1.setName(project.getName());
        project1.setDescription(project.getDescription());
        project1.setUser(user);
        projectRepository.save(project1);
        return project1.getIdProjet();
    }

        @GetMapping()
        public ResponseEntity<List<Project>> getRegisteredProjects(@RequestParam String email) {
        System.out.println(email);
        User user=userRepository.findByEmail(email);
        List<Project> projects=new ArrayList<>(user.getProjects());
        return ResponseEntity.ok(projects);
        }
//        @GetMapping()
//        public ResponseEntity<List<Scenario>>getRegisteredScenarioByIdProject(@RequestParam int id){
//            System.out.println(id);
//            return null;
//        }
}
