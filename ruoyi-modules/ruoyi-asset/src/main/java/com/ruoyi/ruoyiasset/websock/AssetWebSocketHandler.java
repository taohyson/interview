package com.ruoyi.ruoyiasset.websock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.ruoyiasset.domain.Asset;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class AssetWebSocketHandler extends TextWebSocketHandler {
    private List<WebSocketSession> sessions = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming messages if necessary
    }

    public void notifyPriceChange(Asset anAssert) {
        for (WebSocketSession session : sessions) {
            try {
                String payload = objectMapper.writeValueAsString(anAssert);
                session.sendMessage(new TextMessage(payload));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
