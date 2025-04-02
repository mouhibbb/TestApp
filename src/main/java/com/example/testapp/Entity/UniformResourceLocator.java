package com.example.testapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Table(name = "_url")
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class UniformResourceLocator {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUniformResourceLocator;

    private String url;

    public void setIdUrl(Long idUrl) {
        this.idUniformResourceLocator = idUrl;
    }
    // Setter
    public void setUrl(String url) {
        this.url = url;
    }
    public Long getIdUrl() {
        return idUniformResourceLocator;
    }

    public String getUrl() {
        return url;
    }
}
