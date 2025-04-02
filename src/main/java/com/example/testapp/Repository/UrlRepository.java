package com.example.testapp.Repository;

import com.example.testapp.Entity.UniformResourceLocator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UniformResourceLocator, Long> {
}
