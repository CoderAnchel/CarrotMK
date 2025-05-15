package org.lince.pulsarbrokerjvm.Core.Entities;

import org.lince.pulsarbrokerjvm.Configuration.Entities.QueueConfig;

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
