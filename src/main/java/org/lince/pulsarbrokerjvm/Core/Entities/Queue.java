package org.lince.pulsarbrokerjvm.Core.Entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lince.pulsarbrokerjvm.Configuration.Entities.QueueConfig;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Queue {
    private String QueueName;
    private ArrayList<Message> messages;
    private QueueConfig config;
    private HashMap<String, Consumer> listeners;

    public Queue(String QueueName, QueueConfig config) {
        this.QueueName = QueueName;
        this.messages = new ArrayList<>();
        this.listeners = new HashMap<>();
        this.config = config;
    }

    public void newMessage(Message message) throws IOException {
        messages.add(message);

        broadcast(message);
        System.out.println("Message added!");
    }

    //Optimizar (Threads)
    public void broadcast(Message message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String messageJson = objectMapper.writeValueAsString(message);

        for (Consumer consumer : listeners.values()) {
            consumer.getSession().sendMessage(new TextMessage(messageJson));
        }
    }

    public void addListener(Consumer consumer) {
        this.listeners.put(consumer.getConsumerId(), consumer);
        System.out.println("Consumer added!");
    }

    public String getQueueName() {
        return QueueName;
    }

    public void setQueueName(String queueName) {
        QueueName = queueName;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public QueueConfig getConfig() {
        return config;
    }

    public void setConfig(QueueConfig config) {
        this.config = config;
    }

    public HashMap<String, Consumer> getListeners() {
        return listeners;
    }

    public void setListeners(
            HashMap<String, Consumer> listeners) {
        this.listeners = listeners;
    }
}
