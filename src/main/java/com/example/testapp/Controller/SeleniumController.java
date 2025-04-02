package com.example.testapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/selenium")
public class SeleniumController {
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//    @PostMapping("/inputs")
//    public ResponseEntity<String> receiveInputs(@RequestBody List<Map<String,String>>inputs){
//        System.out.println("champ recu selenium " + inputs);
//        messagingTemplate.convertAndSend("/topic/angular", inputs);
//        return ResponseEntity.ok("champ envoye avec succes");
//    }
}
