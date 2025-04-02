package com.example.testapp.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class ScenarioInputDTO {
    private String name;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    // Constructeur par défaut
    public ScenarioInputDTO() {}

    // Constructeur avec paramètres
    public ScenarioInputDTO(String name, String value,String type) {
        this.name = name;
        this.value = value;
        this.type  = type;

    }

    // Getters et Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    // Affichage des données (utile pour le debug)
    @Override
    public String toString() {
        return "ScenarioInputDTO{name='" + name + "', value='" + value + "'}";
    }
}
