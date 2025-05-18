package org.lince.pulsarbrokerjvm.Configuration.Entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lince.pulsarbrokerjvm.Utils.Json;

import java.util.List;
import java.util.Map;

public class ConsumerConfig {
    // Consumer Identification
    private String consumerId;
    private String name;
    private String description;
    private long creationTimestamp;
    private String consumerType;

    // Connection Settings
    private int connectionTimeout;
    private int idleTimeout;
    private String reconnectionPolicy;
    private int keepAliveInterval;
    private int maxConnectionLifetime;

    // Subscription Settings
    private String subscriptionMode;
    private String initialPosition;
    private List<String> topicFilterPatterns;
    private int subscriptionExpirationTime;
    private int autoUnsubscribeAfterInactivity;

    // Message Consumption Settings
    private int maxInFlightMessages;
    private int acknowledgmentTimeout;
    private String acknowledgmentMode;
    private int messagePrefetchCount;
    private int consumerPriority;

    // Processing Controls
    private int rateLimiting;
    private int minBatchSize;
    private int maxBatchSize;
    private List<String> messageFilteringRules;
    private int deliveryDelay;

    // Error Handling
    private String deadLetterPolicy;
    private int maxDeliveryAttempts;
    private String retryDelayStrategy;
    private List<String> errorNotificationPreferences;

    // Security & Permissions
    private List<String> authorizedTopics;
    private boolean readOnlyAccess;
    private boolean canCreateTopics;
    private List<String> ipAddressRestrictions;
    private String authenticationCredentials;

    // Monitoring & Metrics
    private String metricsCollectionLevel;
    private Map<String, String> customTags;
    private String activityLoggingPreferences;
    private Map<String, Integer> notificationThresholds;

    public ConsumerConfig(String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode payloadNode = objectMapper.readTree(payload);

            if (payloadNode != null) {
                Json<ConsumerConfig> jsonUtil = new Json<>();
                jsonUtil.populateFieldsFromJson(this, payloadNode);
            } else {
                System.out.println("El nodo 'payload' no contiene los campos esperados.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR READING payload");
        }
    }

    public ConsumerConfig deepClone() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(this);
            return objectMapper.readValue(json, ConsumerConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while cloning ConsumerConfig", e);
        }
    }

    public Map<String, Integer> getNotificationThresholds() {
        return notificationThresholds;
    }

    public void setNotificationThresholds(
            Map<String, Integer> notificationThresholds) {
        this.notificationThresholds = notificationThresholds;
    }

    public String getActivityLoggingPreferences() {
        return activityLoggingPreferences;
    }

    public void setActivityLoggingPreferences(String activityLoggingPreferences) {
        this.activityLoggingPreferences = activityLoggingPreferences;
    }

    public Map<String, String> getCustomTags() {
        return customTags;
    }

    public void setCustomTags(Map<String, String> customTags) {
        this.customTags = customTags;
    }

    public String getMetricsCollectionLevel() {
        return metricsCollectionLevel;
    }

    public void setMetricsCollectionLevel(String metricsCollectionLevel) {
        this.metricsCollectionLevel = metricsCollectionLevel;
    }

    public String getAuthenticationCredentials() {
        return authenticationCredentials;
    }

    public void setAuthenticationCredentials(String authenticationCredentials) {
        this.authenticationCredentials = authenticationCredentials;
    }

    public List<String> getIpAddressRestrictions() {
        return ipAddressRestrictions;
    }

    public void setIpAddressRestrictions(List<String> ipAddressRestrictions) {
        this.ipAddressRestrictions = ipAddressRestrictions;
    }

    public boolean isCanCreateTopics() {
        return canCreateTopics;
    }

    public void setCanCreateTopics(boolean canCreateTopics) {
        this.canCreateTopics = canCreateTopics;
    }

    public boolean isReadOnlyAccess() {
        return readOnlyAccess;
    }

    public void setReadOnlyAccess(boolean readOnlyAccess) {
        this.readOnlyAccess = readOnlyAccess;
    }

    public List<String> getAuthorizedTopics() {
        return authorizedTopics;
    }

    public void setAuthorizedTopics(List<String> authorizedTopics) {
        this.authorizedTopics = authorizedTopics;
    }

    public List<String> getErrorNotificationPreferences() {
        return errorNotificationPreferences;
    }

    public void setErrorNotificationPreferences(
            List<String> errorNotificationPreferences) {
        this.errorNotificationPreferences = errorNotificationPreferences;
    }

    public String getRetryDelayStrategy() {
        return retryDelayStrategy;
    }

    public void setRetryDelayStrategy(String retryDelayStrategy) {
        this.retryDelayStrategy = retryDelayStrategy;
    }

    public int getMaxDeliveryAttempts() {
        return maxDeliveryAttempts;
    }

    public void setMaxDeliveryAttempts(int maxDeliveryAttempts) {
        this.maxDeliveryAttempts = maxDeliveryAttempts;
    }

    public String getDeadLetterPolicy() {
        return deadLetterPolicy;
    }

    public void setDeadLetterPolicy(String deadLetterPolicy) {
        this.deadLetterPolicy = deadLetterPolicy;
    }

    public int getDeliveryDelay() {
        return deliveryDelay;
    }

    public void setDeliveryDelay(int deliveryDelay) {
        this.deliveryDelay = deliveryDelay;
    }

    public List<String> getMessageFilteringRules() {
        return messageFilteringRules;
    }

    public void setMessageFilteringRules(List<String> messageFilteringRules) {
        this.messageFilteringRules = messageFilteringRules;
    }

    public int getMaxBatchSize() {
        return maxBatchSize;
    }

    public void setMaxBatchSize(int maxBatchSize) {
        this.maxBatchSize = maxBatchSize;
    }

    public int getMinBatchSize() {
        return minBatchSize;
    }

    public void setMinBatchSize(int minBatchSize) {
        this.minBatchSize = minBatchSize;
    }

    public int getRateLimiting() {
        return rateLimiting;
    }

    public void setRateLimiting(int rateLimiting) {
        this.rateLimiting = rateLimiting;
    }

    public int getConsumerPriority() {
        return consumerPriority;
    }

    public void setConsumerPriority(int consumerPriority) {
        this.consumerPriority = consumerPriority;
    }

    public int getMessagePrefetchCount() {
        return messagePrefetchCount;
    }

    public void setMessagePrefetchCount(int messagePrefetchCount) {
        this.messagePrefetchCount = messagePrefetchCount;
    }

    public String getAcknowledgmentMode() {
        return acknowledgmentMode;
    }

    public void setAcknowledgmentMode(String acknowledgmentMode) {
        this.acknowledgmentMode = acknowledgmentMode;
    }

    public int getAcknowledgmentTimeout() {
        return acknowledgmentTimeout;
    }

    public void setAcknowledgmentTimeout(int acknowledgmentTimeout) {
        this.acknowledgmentTimeout = acknowledgmentTimeout;
    }

    public int getMaxInFlightMessages() {
        return maxInFlightMessages;
    }

    public void setMaxInFlightMessages(int maxInFlightMessages) {
        this.maxInFlightMessages = maxInFlightMessages;
    }

    public int getAutoUnsubscribeAfterInactivity() {
        return autoUnsubscribeAfterInactivity;
    }

    public void setAutoUnsubscribeAfterInactivity(int autoUnsubscribeAfterInactivity) {
        this.autoUnsubscribeAfterInactivity = autoUnsubscribeAfterInactivity;
    }

    public int getSubscriptionExpirationTime() {
        return subscriptionExpirationTime;
    }

    public void setSubscriptionExpirationTime(int subscriptionExpirationTime) {
        this.subscriptionExpirationTime = subscriptionExpirationTime;
    }

    public List<String> getTopicFilterPatterns() {
        return topicFilterPatterns;
    }

    public void setTopicFilterPatterns(List<String> topicFilterPatterns) {
        this.topicFilterPatterns = topicFilterPatterns;
    }

    public String getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(String initialPosition) {
        this.initialPosition = initialPosition;
    }

    public String getSubscriptionMode() {
        return subscriptionMode;
    }

    public void setSubscriptionMode(String subscriptionMode) {
        this.subscriptionMode = subscriptionMode;
    }

    public int getMaxConnectionLifetime() {
        return maxConnectionLifetime;
    }

    public void setMaxConnectionLifetime(int maxConnectionLifetime) {
        this.maxConnectionLifetime = maxConnectionLifetime;
    }

    public int getKeepAliveInterval() {
        return keepAliveInterval;
    }

    public void setKeepAliveInterval(int keepAliveInterval) {
        this.keepAliveInterval = keepAliveInterval;
    }

    public String getReconnectionPolicy() {
        return reconnectionPolicy;
    }

    public void setReconnectionPolicy(String reconnectionPolicy) {
        this.reconnectionPolicy = reconnectionPolicy;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(String consumerType) {
        this.consumerType = consumerType;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public String toString() {
        return "ConsumerConfig{" +
                "consumerId='" + consumerId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                ", consumerType='" + consumerType + '\'' +
                ", connectionTimeout=" + connectionTimeout +
                ", idleTimeout=" + idleTimeout +
                ", reconnectionPolicy='" + reconnectionPolicy + '\'' +
                ", keepAliveInterval=" + keepAliveInterval +
                ", maxConnectionLifetime=" + maxConnectionLifetime +
                ", subscriptionMode='" + subscriptionMode + '\'' +
                ", initialPosition='" + initialPosition + '\'' +
                ", topicFilterPatterns=" + topicFilterPatterns +
                ", subscriptionExpirationTime=" + subscriptionExpirationTime +
                ", autoUnsubscribeAfterInactivity=" + autoUnsubscribeAfterInactivity +
                ", maxInFlightMessages=" + maxInFlightMessages +
                ", acknowledgmentTimeout=" + acknowledgmentTimeout +
                ", acknowledgmentMode='" + acknowledgmentMode + '\'' +
                ", messagePrefetchCount=" + messagePrefetchCount +
                ", consumerPriority=" + consumerPriority +
                ", rateLimiting=" + rateLimiting +
                ", minBatchSize=" + minBatchSize +
                ", maxBatchSize=" + maxBatchSize +
                ", messageFilteringRules=" + messageFilteringRules +
                ", deliveryDelay=" + deliveryDelay +
                ", deadLetterPolicy='" + deadLetterPolicy + '\'' +
                ", maxDeliveryAttempts=" + maxDeliveryAttempts +
                ", retryDelayStrategy='" + retryDelayStrategy + '\'' +
                ", errorNotificationPreferences=" + errorNotificationPreferences +
                ", authorizedTopics=" + authorizedTopics +
                ", readOnlyAccess=" + readOnlyAccess +
                ", canCreateTopics=" + canCreateTopics +
                ", ipAddressRestrictions=" + ipAddressRestrictions +
                ", authenticationCredentials='" + authenticationCredentials + '\'' +
                ", metricsCollectionLevel='" + metricsCollectionLevel + '\'' +
                ", customTags=" + customTags +
                ", activityLoggingPreferences='" + activityLoggingPreferences + '\'' +
                ", notificationThresholds=" + notificationThresholds +
                '}';
    }
}