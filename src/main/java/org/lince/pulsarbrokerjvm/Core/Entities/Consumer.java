package org.lince.pulsarbrokerjvm.Core.Entities;

import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

public class Consumer {
    private String consumerId;
    private WebSocketSession session;
    private ArrayList<String> connections;

    public Consumer( String consumerId, WebSocketSession session) {
        this.consumerId = consumerId;
        this.session = session;
        this.connections = new ArrayList<>();
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "consumerId='" + consumerId + '\'' +
                ", session=" + session +
                ", connections=" + connections +
                '}';
    }
}
