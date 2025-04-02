package com.example.testapp.Repository;

import com.example.testapp.Entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

public interface ScenarioRepository extends JpaRepository<Scenario,Long> {
@Query("select s from Scenario s LEFT join fetch s.inputs where s.idScenario=:id")
Scenario findByIdWithInputs(@Param("id") Long id);
}