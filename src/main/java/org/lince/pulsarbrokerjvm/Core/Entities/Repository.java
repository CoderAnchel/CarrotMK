package org.lince.pulsarbrokerjvm.Core.Entities;

import org.lince.pulsarbrokerjvm.Configuration.Entities.RepositoryConfig;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Repository {
    private String RepoName;
    private ConcurrentHashMap<String, Queue> topics;
    private HashMap<String, Consumer> listeners;
    private RepositoryConfig repositoryConfig;
    private long actualSize;

    public Repository( String RepoName, RepositoryConfig repositoryConfig) {
        this.RepoName = RepoName;
        this.topics = new ConcurrentHashMap<>();
        this.listeners = new HashMap<>();
        this.repositoryConfig = repositoryConfig;
    }

    public void addListener(Consumer consumer) {
        this.listeners.put(consumer.getConsumerId(), consumer.clone());
    }

    public String getRepoName() {
        return RepoName;
    }

    public void setRepoName(String repoName) {
        RepoName = repoName;
    }

    public  ConcurrentHashMap<String, org.lince.pulsarbrokerjvm.Core.Entities.Queue> getTopics() {
        return topics;
    }

    public void setTopics( ConcurrentHashMap<String, org.lince.pulsarbrokerjvm.Core.Entities.Queue> topics) {
        this.topics = topics;
    }

    public HashMap<String, Consumer> getListeners() {
        return listeners;
    }

    public void setListeners(
            HashMap<String, Consumer> listeners) {
        this.listeners = listeners;
    }

    public RepositoryConfig getRepositoryConfig() {
        return repositoryConfig;
    }

    public void setRepositoryConfig(
            RepositoryConfig repositoryConfig) {
        this.repositoryConfig = repositoryConfig;
    }

    public void addQueue(Queue queue) {
        if (maxQueues()) {
            this.topics.put(queue.getQueueName(), queue);
        } else if (repositoryConfig.getMaxQueues() == 0) {
            this.topics.put(queue.getQueueName(), queue);
        }
    }

    public boolean maxQueues() {
        return topics.size() < repositoryConfig.getMaxQueues();
    }

    public long getActualSize() {
        return actualSize;
    }

    public void setActualSize(long actualSize) {
        this.actualSize = actualSize;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "RepoName='" + RepoName + '\'' +
                ", topics=" + topics.toString() +
                ", listeners=" + listeners.toString() +
                ", repositoryConfig=" + repositoryConfig.toString() +
                '}';
    }
}
