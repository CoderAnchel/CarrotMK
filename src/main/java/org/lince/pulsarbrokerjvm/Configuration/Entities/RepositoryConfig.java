package org.lince.pulsarbrokerjvm.Configuration.Entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lince.pulsarbrokerjvm.Utils.Json;
import org.springframework.web.socket.TextMessage;

import java.util.Date;
import java.util.List;

public class RepositoryConfig {
    // Basic Settings
    private String name;
    private String description;
    private Date creationTimestamp;
    private String ownerId;

    // Security & Access Control
    private String authenticationType; // none, basic, token-based
    private List<String> accessControlList; // List of users/roles
    private List<String> ipAllowList;
    private List<String> ipDenyList;

    // Operational Settings
    private int maxQueues;
    private QueueConfig defaultQueueSettings;
    private boolean autoCreateQueues;
    private String metricsCollectionLevel; // none, basic, detailed

    // Retention Policies
    private long defaultMessageTTL; // in milliseconds
    private long maxStorageSize; // in bytes
    private String inactiveRepositoryCleanupPolicy;

    public RepositoryConfig(String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode payloadNode = objectMapper.readTree(payload);

            if (payloadNode != null) {
                Json<RepositoryConfig> jsonUtil = new Json<>();
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

    public RepositoryConfig(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    public List<String> getAccessControlList() {
        return accessControlList;
    }

    public void setAccessControlList(List<String> accessControlList) {
        this.accessControlList = accessControlList;
    }

    public List<String> getIpAllowList() {
        return ipAllowList;
    }

    public void setIpAllowList(List<String> ipAllowList) {
        this.ipAllowList = ipAllowList;
    }

    public List<String> getIpDenyList() {
        return ipDenyList;
    }

    public void setIpDenyList(List<String> ipDenyList) {
        this.ipDenyList = ipDenyList;
    }

    public int getMaxQueues() {
        return maxQueues;
    }

    public void setMaxQueues(int maxQueues) {
        this.maxQueues = maxQueues;
    }

    public QueueConfig getDefaultQueueSettings() {
        return defaultQueueSettings;
    }

    public void setDefaultQueueSettings(
            QueueConfig defaultQueueSettings) {
        this.defaultQueueSettings = defaultQueueSettings;
    }

    public boolean isAutoCreateQueues() {
        return autoCreateQueues;
    }

    public void setAutoCreateQueues(boolean autoCreateQueues) {
        this.autoCreateQueues = autoCreateQueues;
    }

    public String getMetricsCollectionLevel() {
        return metricsCollectionLevel;
    }

    public void setMetricsCollectionLevel(String metricsCollectionLevel) {
        this.metricsCollectionLevel = metricsCollectionLevel;
    }

    public long getDefaultMessageTTL() {
        return defaultMessageTTL;
    }

    public void setDefaultMessageTTL(long defaultMessageTTL) {
        this.defaultMessageTTL = defaultMessageTTL;
    }

    public long getMaxStorageSize() {
        return maxStorageSize;
    }

    public void setMaxStorageSize(long maxStorageSize) {
        this.maxStorageSize = maxStorageSize;
    }

    public String getInactiveRepositoryCleanupPolicy() {
        return inactiveRepositoryCleanupPolicy;
    }

    public void setInactiveRepositoryCleanupPolicy(String inactiveRepositoryCleanupPolicy) {
        this.inactiveRepositoryCleanupPolicy = inactiveRepositoryCleanupPolicy;
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
        return "RepositoryConfig{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                ", ownerId='" + ownerId + '\'' +
                ", authenticationType='" + authenticationType + '\'' +
                ", accessControlList=" + accessControlList +
                ", ipAllowList=" + ipAllowList +
                ", ipDenyList=" + ipDenyList +
                ", maxQueues=" + maxQueues +
                ", defaultQueueSettings=" + defaultQueueSettings +
                ", autoCreateQueues=" + autoCreateQueues +
                ", metricsCollectionLevel='" + metricsCollectionLevel + '\'' +
                ", defaultMessageTTL=" + defaultMessageTTL +
                ", maxStorageSize=" + maxStorageSize +
                ", inactiveRepositoryCleanupPolicy='" + inactiveRepositoryCleanupPolicy + '\'' +
                '}';
    }
}
