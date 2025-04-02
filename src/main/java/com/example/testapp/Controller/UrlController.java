package com.example.testapp.Controller;

import com.example.testapp.Entity.UniformResourceLocator;
import com.example.testapp.Repository.UrlRepository;
import com.example.testapp.Service.UrlService;
import com.example.testapp.Controller.UrlWebSocketController; // ✅ Import WebSocket Controller
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/url")
public class UrlController {
    private final UrlService urlService;
    private final UrlRepository urlRepository;
    private final UrlWebSocketController urlWebSocketController;  // ✅ Inject WebSocket Controller

    public UrlController(UrlService urlService, UrlRepository urlRepository, UrlWebSocketController urlWebSocketController) {
        this.urlService = urlService;
        this.urlRepository = urlRepository;
        this.urlWebSocketController = urlWebSocketController;
    }

    @PostMapping
    public List<Map<String, String>> createUrl(@RequestBody UniformResourceLocator urlEntity) {
        try {
            // 1️⃣ Enregistrer l'URL dans la base de données
            System.out.println(" Nouvelle URL enregistrée : " + urlEntity.getUrl());
            urlRepository.save(urlEntity);
            Map<String,Object>message=new HashMap<>();
            message.put("type","NAVIGATION");
            message.put("url",urlEntity.getUrl());
            urlWebSocketController.sendMessageToClients(message);  // Appeler WebSocket pour envoyer l'URL
            int maxRetries =30; // Nombre maximum de tentatives
            int delayMs = 500; // Délai entre chaque tentative en millisecondes
            List<Map<String,String>> inputs;
            for (int i=0;i<maxRetries;i++){
                System.out.println("i="+i);
                inputs=urlWebSocketController.getReceivedInputs();
                if (!inputs.isEmpty()){
                    List<Map<String ,String >> result=new ArrayList<>(inputs);
                    inputs.clear();
                    return result;
                }
                Thread.sleep(delayMs);
            }
            Map<String , String> errorResponse =new HashMap<>();
            errorResponse.put("message", "Timeout: Les inputs ne sont pas disponibles.");
          return  Collections.singletonList(errorResponse);
           // return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String , String> errorResponse=new HashMap<>();
            errorResponse.put("message","Erreur lors de l'enregistrement de l'URL.");
            return Collections.singletonList(errorResponse);
        }
    }
    @GetMapping
    public List<Map<String, String>> getInputs() {
        return urlWebSocketController.getReceivedInputs();
    }
}
