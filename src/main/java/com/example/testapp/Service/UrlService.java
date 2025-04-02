package com.example.testapp.Service;

import com.example.testapp.Entity.UniformResourceLocator;
import com.example.testapp.Repository.UrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Service
public class UrlService {
    private static final Set<WebSocketSession> sessions = new HashSet<>();

    private final UrlRepository urlRepository;
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UniformResourceLocator createUrl(UniformResourceLocator url) throws  Exception{
        for(WebSocketSession session:sessions){
            if (session.isOpen()){
                String jsonMessage=url.getUrl();
                session.sendMessage(new TextMessage(jsonMessage));
            }
        }
        return urlRepository.save(url);

    }
    public static void addSession(WebSocketSession session) {
        sessions.add(session);
    }

}
