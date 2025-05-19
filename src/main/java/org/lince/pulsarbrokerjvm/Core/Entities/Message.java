package org.lince.pulsarbrokerjvm.Core.Entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lince.pulsarbrokerjvm.Core.PulsarBroker;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Message {
    private int messageId;
    private String producerId;
    private String repo;
    private String queue;
    private String contentType;
    private String payload;
    private Date timestampt;
    private Long size;

    public Message(String payload, WebSocketSession session) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(payload);


            //need to add the validation! (queue and repo existence)
            if (node.has("repo") && node.has("topic") && node.has("content_type") && node.has(
                    "payload")) {
                this.messageId = PulsarBroker.increment();
                this.producerId = session.getId();
                this.repo = node.get("repo").asText();
                this.queue = node.get("topic").asText();
                this.contentType = node.get("content_type").asText();
                // Convertir el nodo "payload" a un string JSON si es un objeto
                JsonNode payloadNode = node.get("payload");
                this.payload = payloadNode.isObject() ? payloadNode.toString() : payloadNode.asText();

                this.timestampt = new Date();
                this.size = (long) payload.getBytes(StandardCharsets.UTF_8).length;
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Send error response back to client
            session.sendMessage(new TextMessage("Error processing message: " + e.getMessage()));
        }
    }

    public Message(String repo, String queue, String contentType, String payload) {
        this.repo = repo;
        this.queue = queue;
        this.contentType = contentType;
        this.payload = payload;
    }


    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Date getTimestampt() {
        return timestampt;
    }

    public void setTimestampt(Date timestampt) {
        this.timestampt = timestampt;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }


    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", producerId='" + producerId + '\'' +
                ", repo='" + repo + '\'' +
                ", queue='" + queue + '\'' +
                ", contentType='" + contentType + '\'' +
                ", payload='" + payload + '\'' +
                ", timestampt=" + timestampt +
                ", size=" + size +
                '}';
    }
}
