package com.example.testapp.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
public class ScenarioInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInput;

    private String name;  // Nom du champ input
    private String value; // Valeur saisie
    private String type; // Valeur saisie

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    @JsonBackReference
    private Scenario scenario;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ScenarioInput(String name, String value, Scenario scenario, String type) {
        this.name = name;

        this.value = value;
        this.type = type;
        this.scenario = scenario;
    }

    public ScenarioInput() {
    }


    // Getters et Setters
    public Long getId() { return idInput; }
    public void setId(Long id) { this.idInput = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public Scenario getScenario() { return scenario; }
    public void setScenario(Scenario scenario) { this.scenario = scenario; }
}
