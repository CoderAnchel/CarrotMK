package org.lince.pulsarbrokerjvm.Core.Network;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lince.pulsarbrokerjvm.Core.Entities.Consumer;
import org.lince.pulsarbrokerjvm.Core.Entities.Message;
import org.lince.pulsarbrokerjvm.Core.PulsarBroker;
import org.lince.pulsarbrokerjvm.Core.Services.MessagingService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Basic Spring WebSocket handler with just handshake, message handling and close
 */
@Component
public class wsHandler extends TextWebSocketHandler {

    /**
     * Called after WebSocket connection is established (handshake complete)
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connection established: " + session.getId());
        PulsarBroker.addConsumer(new Consumer(session.getId(), session));
    }

    /**
     * Handle incoming messages from client
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        MessagingService.filterMessage(session, message);
    }

    /**
     * Called after WebSocket connection is closed
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection closed: " + session.getId() + ", status: " + status.getReason());
    }
}