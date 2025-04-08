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



    private Long projectId;


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public List<ScenarioInputDTO> getInputs() { return inputs; }
    public void setInputs(List<ScenarioInputDTO> inputs) { this.inputs = inputs; }

    @Override
    public String toString() {
        return "ScenarioRequest{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", inputs=" + inputs +
                ", projectId=" + projectId +
                '}';
    }
}
