package com.example.testapp.Controller;

import com.example.testapp.DTO.ScenarioInputDTO;
import com.example.testapp.Entity.Scenario;
import com.example.testapp.Entity.*;

import com.example.testapp.Repository.ScenarioInputRepository;
import com.example.testapp.Repository.ScenarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/scenarios")
public class ScenarioController {

    @Autowired
    private ScenarioRepository scenarioRepository;

    @Autowired
    private ScenarioInputRepository scenarioInputRepository;
    @Autowired
    private UrlWebSocketController urlWebSocketController;

    @PostMapping()
    public Scenario saveScenario(@RequestBody ScenarioRequest request) {
        System.out.println(request.getProjectId(),request., request.getUrl());
        Scenario scenario = new Scenario(request.getName(), request.getUrl());
        scenario = scenarioRepository.save(scenario);

        for (ScenarioInputDTO inputDTO : request.getInputs()) {
            ScenarioInput input = new ScenarioInput(inputDTO.getName(), inputDTO.getValue(), scenario,inputDTO.getType());
            scenarioInputRepository.save(input);
        }

        return scenario;
    }


    @GetMapping()
    public List<Scenario> getAllScenarios() {
        return scenarioRepository.findAll();
    }

    @PostMapping("/{scenarioId}")
    public ResponseEntity<Scenario> getSelectedSceanrio(@PathVariable Long scenarioId){
        System.out.println("scenarioId"+scenarioId);

         Scenario scenario=scenarioRepository.findByIdWithInputs(scenarioId);

        String scenarioJson=convertToJson(scenario);
        Map<String,Object> navigationMessage=new HashMap<>();
        navigationMessage.put("type","FILL_FORM");
        navigationMessage.put("scenario",convertToJson(scenario));
        try{
            System.out.println("sendMessageToClients"+navigationMessage);
        urlWebSocketController.sendMessageToClients(navigationMessage);
            }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Erreur lors de l'envoi du message WebSocket"+e.getMessage());
        }

        return ResponseEntity.ok(scenario);
    }
    private String convertToJson(Scenario scenario) {
        try {
            return new ObjectMapper().writeValueAsString(scenario);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

}
