package org.lince.pulsarbrokerjvm.Core.Entities;

import org.lince.pulsarbrokerjvm.Configuration.Entities.RepositoryConfig;

import java.util.HashMap;
import java.util.Queue;

public class Repository {
    private String RepoName;
    private HashMap<String, org.lince.pulsarbrokerjvm.Core.Entities.Queue> topics;
    private HashMap<String, Consumer> listeners;
    private RepositoryConfig repositoryConfig;

    public Repository( String RepoName, RepositoryConfig repositoryConfig) {
        this.RepoName = RepoName;
        this.topics = new HashMap<>();
        this.listeners = new HashMap<>();
        this.repositoryConfig = repositoryConfig;
    }

    public void addListener(Consumer consumer) {
        this.listeners.put(consumer.getConsumerId(), consumer);
    }

    public String getRepoName() {
        return RepoName;
    }

    public void setRepoName(String repoName) {
        RepoName = repoName;
    }

    public  HashMap<String, org.lince.pulsarbrokerjvm.Core.Entities.Queue> getTopics() {
        return topics;
    }

    public void setTopics( HashMap<String, org.lince.pulsarbrokerjvm.Core.Entities.Queue> topics) {
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
}
