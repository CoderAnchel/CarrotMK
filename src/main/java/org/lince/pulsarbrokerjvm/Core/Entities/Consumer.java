package org.lince.pulsarbrokerjvm.Core.Entities;

import org.springframework.web.socket.WebSocketSession;

public class Consumer {
    private String consumerId;
    private WebSocketSession session;

    public Consumer( String consumerId, WebSocketSession session) {
        this.consumerId = consumerId;
        this.session = session;
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
}
