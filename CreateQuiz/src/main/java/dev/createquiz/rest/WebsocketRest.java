package dev.createquiz.rest;

import com.google.gson.Gson;
import dev.createquiz.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebsocketRest {
    private final SimpMessagingTemplate messagingTemplate;
    private final Gson gson;

    public void sendServiceStatus(MessageDTO message) {
        messagingTemplate.convertAndSend("/topic/service-status", gson.toJson(message));
    }
}