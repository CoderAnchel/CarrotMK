package org.lince.pulsarbrokerjvm.Configuration.Entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QueueConfig {
    private String name;
    private String description;

    public QueueConfig(String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode payloadNode = objectMapper.readTree(payload);

            // Accede directamente a los campos "name" y "description"
            if (payloadNode != null && payloadNode.has("name") && payloadNode.has("description")) {
                this.name = payloadNode.get("name").asText();
                this.description = payloadNode.get("description").asText();
            } else {
                System.out.println("El nodo 'payload' no contiene los campos esperados.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR READING payload");
        }
    }

    public QueueConfig(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "QueueConfig{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
