package com.example.testapp.Entity;

import com.example.testapp.DTO.ScenarioInputDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioRequest {
    private String name;
    private String url;
    private List<ScenarioInputDTO> inputs;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    private int projectId;
    // Getters et Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public List<ScenarioInputDTO> getInputs() { return inputs; }
    public void setInputs(List<ScenarioInputDTO> inputs) { this.inputs = inputs; }
}
