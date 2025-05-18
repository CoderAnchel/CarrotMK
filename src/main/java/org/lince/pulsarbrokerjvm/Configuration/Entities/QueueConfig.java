package org.lince.pulsarbrokerjvm.Configuration.Entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lince.pulsarbrokerjvm.Utils.Json;

import java.util.Date;
import java.util.List;

public class QueueConfig {
    // Basic Settings
    private String name;
    private String description;
    private Date creationTimestamp;
    private String queueType; // standard, FIFO, priority

    // Message Handling
    private long messageTTL; // in milliseconds
    private long maxMessageSize; // in bytes
    private long maxQueueSize; // in number of messages or bytes
    private boolean messageDeduplication; // enabled/disabled
    private List<String> contentTypeValidationRules;

    // Delivery Options
    private String deliveryRetryPolicy; // attempts, delays
    private String deadLetterQueueConfig;
    private String messageOrderingGuarantees; // strict or relaxed
    private String consumerAcknowledgmentRequirements;

    // Performance Settings
    private int maxConsumersAllowed;
    private int producerRateLimit; // messages per second
    private int consumerRateLimit; // messages per second
    private String messageBatchingSettings;

    // Persistence Options
    private String durability; // in-memory only, persistent to disk
    private int replicationFactor; // for clustering
    private String messagePersistenceStrategy; // all messages, only undelivered

    public QueueConfig(String payload) {
          try {
              ObjectMapper objectMapper = new ObjectMapper();
              JsonNode payloadNode = objectMapper.readTree(payload);

              if (payloadNode != null) {
                  Json<QueueConfig> jsonUtil = new Json<>();
                  jsonUtil.populateFieldsFromJson(this, payloadNode);
                  this.creationTimestamp = new Date();
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

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public long getMessageTTL() {
        return messageTTL;
    }

    public void setMessageTTL(long messageTTL) {
        this.messageTTL = messageTTL;
    }

    public long getMaxMessageSize() {
        return maxMessageSize;
    }

    public void setMaxMessageSize(long maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    public long getMaxQueueSize() {
        return maxQueueSize;
    }

    public void setMaxQueueSize(long maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public boolean isMessageDeduplication() {
        return messageDeduplication;
    }

    public void setMessageDeduplication(boolean messageDeduplication) {
        this.messageDeduplication = messageDeduplication;
    }

    public List<String> getContentTypeValidationRules() {
        return contentTypeValidationRules;
    }

    public void setContentTypeValidationRules(List<String> contentTypeValidationRules) {
        this.contentTypeValidationRules = contentTypeValidationRules;
    }

    public String getDeliveryRetryPolicy() {
        return deliveryRetryPolicy;
    }

    public void setDeliveryRetryPolicy(String deliveryRetryPolicy) {
        this.deliveryRetryPolicy = deliveryRetryPolicy;
    }

    public String getDeadLetterQueueConfig() {
        return deadLetterQueueConfig;
    }

    public void setDeadLetterQueueConfig(String deadLetterQueueConfig) {
        this.deadLetterQueueConfig = deadLetterQueueConfig;
    }

    public String getMessageOrderingGuarantees() {
        return messageOrderingGuarantees;
    }

    public void setMessageOrderingGuarantees(String messageOrderingGuarantees) {
        this.messageOrderingGuarantees = messageOrderingGuarantees;
    }

    public String getConsumerAcknowledgmentRequirements() {
        return consumerAcknowledgmentRequirements;
    }

    public void setConsumerAcknowledgmentRequirements(String consumerAcknowledgmentRequirements) {
        this.consumerAcknowledgmentRequirements = consumerAcknowledgmentRequirements;
    }

    public int getMaxConsumersAllowed() {
        return maxConsumersAllowed;
    }

    public void setMaxConsumersAllowed(int maxConsumersAllowed) {
        this.maxConsumersAllowed = maxConsumersAllowed;
    }

    public int getProducerRateLimit() {
        return producerRateLimit;
    }

    public void setProducerRateLimit(int producerRateLimit) {
        this.producerRateLimit = producerRateLimit;
    }

    public int getConsumerRateLimit() {
        return consumerRateLimit;
    }

    public void setConsumerRateLimit(int consumerRateLimit) {
        this.consumerRateLimit = consumerRateLimit;
    }

    public String getMessageBatchingSettings() {
        return messageBatchingSettings;
    }

    public void setMessageBatchingSettings(String messageBatchingSettings) {
        this.messageBatchingSettings = messageBatchingSettings;
    }

    public String getDurability() {
        return durability;
    }

    public void setDurability(String durability) {
        this.durability = durability;
    }

    public int getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(int replicationFactor) {
        this.replicationFactor = replicationFactor;
    }

    public String getMessagePersistenceStrategy() {
        return messagePersistenceStrategy;
    }

    public void setMessagePersistenceStrategy(String messagePersistenceStrategy) {
        this.messagePersistenceStrategy = messagePersistenceStrategy;
    }

    @Override
    public String toString() {
        return "QueueConfig{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                ", queueType='" + queueType + '\'' +
                ", messageTTL=" + messageTTL +
                ", maxMessageSize=" + maxMessageSize +
                ", maxQueueSize=" + maxQueueSize +
                ", messageDeduplication=" + messageDeduplication +
                ", contentTypeValidationRules=" + contentTypeValidationRules +
                ", deliveryRetryPolicy='" + deliveryRetryPolicy + '\'' +
                ", deadLetterQueueConfig='" + deadLetterQueueConfig + '\'' +
                ", messageOrderingGuarantees='" + messageOrderingGuarantees + '\'' +
                ", consumerAcknowledgmentRequirements='" + consumerAcknowledgmentRequirements +
                '\'' +
                ", maxConsumersAllowed=" + maxConsumersAllowed +
                ", producerRateLimit=" + producerRateLimit +
                ", consumerRateLimit=" + consumerRateLimit +
                ", messageBatchingSettings='" + messageBatchingSettings + '\'' +
                ", durability='" + durability + '\'' +
                ", replicationFactor=" + replicationFactor +
                ", messagePersistenceStrategy='" + messagePersistenceStrategy + '\'' +
                '}';
    }

}
