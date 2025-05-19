package org.lince.pulsarbrokerjvm.Core.Entities;

import org.lince.pulsarbrokerjvm.Configuration.Entities.ConsumerConfig;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

public class Consumer implements Cloneable {
    private String consumerId;
    private WebSocketSession session;
    private ArrayList<String> connections;
    private ConsumerConfig consumerConfig;

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

    public ConsumerConfig getConsumerConfig() {
        return consumerConfig;
    }

    public void setConsumerConfig(
            ConsumerConfig consumerConfig) {
        this.consumerConfig = consumerConfig;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "consumerId='" + consumerId + '\'' +
                ", session=" + session +
                ", connections=" + connections +
                ", config=" + connections +
                '}';
    }

    @Override
    public Consumer clone() {
        try {
            Consumer cloned = (Consumer) super.clone();
            cloned.connections = new ArrayList<>(this.connections); // Copia profunda de la lista
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}
