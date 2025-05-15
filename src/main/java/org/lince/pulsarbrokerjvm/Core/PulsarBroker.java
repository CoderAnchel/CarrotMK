package org.lince.pulsarbrokerjvm.Core;

import ch.qos.logback.core.net.QueueFactory;
import org.lince.pulsarbrokerjvm.Configuration.Entities.QueueConfig;
import org.lince.pulsarbrokerjvm.Core.Entities.Consumer;
import org.lince.pulsarbrokerjvm.Core.Entities.Message;
import org.lince.pulsarbrokerjvm.Core.Entities.Queue;
import org.lince.pulsarbrokerjvm.Core.Entities.Repository;

import java.util.HashMap;

public class PulsarBroker {
    private static HashMap<String, Consumer> consumers = new HashMap<>();
    private static HashMap<String, Repository> repositories = new HashMap<>();
    private static Integer lastMessageId = 0;


    public static void addConsumer(Consumer consumer) {
        System.out.printf("%s added to consumers ✅ \n",consumer.getConsumerId());
        consumers.put(consumer.getConsumerId(), consumer);
    }

    public static int increment() {
        lastMessageId = lastMessageId + 1;
        return lastMessageId;
    }

    //probably migrating to MessagingService
    public static boolean validateRouting(Message message) {
        return repositories.containsKey(message.getRepo()) &&
                repositories.get(message.getRepo()).getTopics().containsKey(message.getQueue());
    }

    public static boolean validateRouting(Message message, String name ) {
        return repositories.containsKey(message.getRepo()) &&
                repositories.get(message.getRepo()).getTopics().containsKey(name);
    }

    public static void addRepository(Repository repository) {
        if (!repositories.containsKey(repository.getRepoName())) {
            repositories.put(repository.getRepoName(), repository);
            System.out.println("Repo added");
        } else {
            System.out.println("Repo allready exist");
        }
    }

    public static void addQueue(Message message, Queue queue) {
        if (validateRouting(message, queue.getQueueName())) {
            System.out.println("Queue all-ready exist");
        } else {
            if(!repositories.containsKey(message.getRepo())) {
                System.out.println("Repo doesn't exist");
            } else {
                repositories.get(message.getRepo()).getTopics().put(queue.getQueueName(), queue);
                System.out.println("Queue added");
            }
        }
    }
}
