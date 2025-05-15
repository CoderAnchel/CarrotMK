package org.lince.pulsarbrokerjvm.Core.Entities;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class JsonMessage extends Message{
    public JsonMessage(String repo, String queue, String contentType, String payload) {
        super(repo, queue, contentType, payload);
    }
}
