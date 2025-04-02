package com.example.testapp.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Table(name = "_scenario")

public class Scenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idScenario;
private String name;
private String url;
@Temporal(TemporalType.TIMESTAMP)
    private Date createdAt=new Date();
@OneToMany(mappedBy = "scenario",cascade = CascadeType.ALL,orphanRemoval = true)
@JsonManagedReference
private List<ScenarioInput> inputs; // Liste des inputs enregistrés

    public Scenario(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public Scenario() {
    }
    // Getters et Setters
    public Long getId() { return idScenario; }
    public void setId(Long id) { this.idScenario = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public List<ScenarioInput> getInputs() { return inputs; }
    public void setInputs(List<ScenarioInput> inputs) { this.inputs = inputs; }
    @ManyToOne Project project;
}


