package com.example.testapp.Controller;

import com.example.testapp.Class.InputField;
import com.example.testapp.Service.UrlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class UrlWebSocketController extends TextWebSocketHandler {
    private final ObjectMapper objectMapper=new ObjectMapper();
    private final UrlService urlService;
    private static final Set<WebSocketSession> sessions = new HashSet<>();

    public UrlWebSocketController(UrlService urlServices) {
        this.urlService = urlServices;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        for (WebSocketSession existingSession :sessions){
            try{
                if (existingSession.isOpen()){
                    existingSession.close();
                    System.out.println("❌ Session WebSocket fermée : " + existingSession.getId());

                }
            }
         catch (IOException e) {
            e.printStackTrace();
        }
        }
        sessions.add(session);
        System.out.println("🔗 Nouvelle connexion WebSocket : " + session.getId());

    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message){
//        System.out.println("from Websocket"+ message.getPayload());
//        List<Map<String,String>>inputList=new Gson().fromJson(message.getPayload(), new TypeToken<List<Map<String,String>>>(){}.getType());
//        receivedInputs.clear();
//        receivedInputs.addAll(inputList);

        // Désérialiser en une liste d'objets `InputField`
        List<InputField> inputList = new Gson().fromJson(
                message.getPayload(),
                new TypeToken<List<InputField>>(){}.getType()
        );

        // Effacer la liste actuelle
        receivedInputs.clear();

        // Convertir `InputField` en `Map<String, String>` et l'ajouter à la liste
        for (InputField field : inputList) {
            Map<String, String> map = new HashMap<>();
            map.put("name", field.getName());
            map.put("placeholder", field.getPlaceholder());
            map.put("type", field.getType());
            map.put("text", field.getText());

            // Pour `options`, il faut convertir la liste en une seule chaîne de texte
            map.put("options", field.getOptions() != null ? String.join(",", field.getOptions()) : "");

            receivedInputs.add(map);
        }

        // Afficher pour vérification
        for (Map<String, String> entry : receivedInputs) {
            System.out.println(entry);
        }

    }

    public List<Map<String, String>> getReceivedInputs() {
        return receivedInputs;
    }
    private  final List<Map<String,String>>receivedInputs=new CopyOnWriteArrayList<>();
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        System.out.println("❌ Connexion WebSocket fermée : " + session.getId());
        System.out.println("📊 Nombre total de connexions : " + sessions.size());

    }
    public void sendMessageToClients(Map<String ,Object> message) throws IOException {
        String jsonMessage = objectMapper.writeValueAsString(message);

        System.out.println("🚀 Envoi de l'URL aux clients : " + jsonMessage);
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(jsonMessage));
            }
        }
    }

    }

