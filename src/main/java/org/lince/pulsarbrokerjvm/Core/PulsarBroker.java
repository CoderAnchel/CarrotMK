package org.lince.pulsarbrokerjvm.Core;

import org.lince.pulsarbrokerjvm.Core.Entities.Consumer;
import org.lince.pulsarbrokerjvm.Core.Entities.Message;
import org.lince.pulsarbrokerjvm.Core.Entities.Queue;
import org.lince.pulsarbrokerjvm.Core.Entities.Repository;
import org.lince.pulsarbrokerjvm.Exceptions.ConsumerNotFoundException;
import org.lince.pulsarbrokerjvm.Exceptions.WrongRouteException;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.HashMap;

public class PulsarBroker {
    private static HashMap<String, Consumer> consumers = new HashMap<>();
    private static HashMap<String, Repository> repositories = new HashMap<>();
    private static Integer lastMessageId = 0;

    public static void addConsumer(Consumer consumer) {
        Consumer csm = consumers.put(consumer.getConsumerId(), consumer);
        if (csm == null) {
            System.out.printf("%s added to consumers ✅ \n",consumer.getConsumerId());
        } else {
            System.out.println("Consumer object updated");
        }
    }

    public static void newMessage(Message mess) throws IOException {
        if (validateRouting(mess)) repositories.get(mess.getRepo()).getTopics().get(mess.getQueue()).newMessage(mess); else System.out.println("Error adding message");
    }

    public static void addListener(String consumerId, Message mess) throws WrongRouteException, ConsumerNotFoundException {
        if (consumers.containsKey(consumerId)) {
            System.out.println("Consumer fined: "+consumers.get(consumerId));

            if (validateRouting(mess)) repositories.get(mess.getRepo()).getTopics().get(mess.getQueue()).addListener(consumers.get(consumerId)); else
                System.out.println("ROUTE NOT FOUND!");
        } else {
            throw new ConsumerNotFoundException("Consumer not found");
        }
    }

    public static void unfollowQueue(Message mess) {
        if (validateRouting(mess)) {
            Queue queue = getQueue(mess);
            queue.unfollow(mess.getProducerId());
        }
    }

    public static void getRepositoryInfo(Message mess) throws IOException  {
        if (repositories.containsKey(mess.getRepo())) {
            Repository repository = repositories.get(mess.getRepo());
            Consumer consumer = repository.getListeners().get(mess.getProducerId());
            if (consumer != null) {
                consumer.getSession().sendMessage(new TextMessage(repository.toString()));
            }
        }
    }

    public static void getQueueInfo(Message mess) throws IOException {
        if (validateRouting(mess)) {
            Queue queue = getQueue(mess);
            Consumer consumer = queue.getListeners().get(mess.getProducerId());
            if (consumer != null) {
                consumer.getSession().sendMessage(new TextMessage(queue.toString()));
            }
        }
    }

    public static Queue getQueue(Message mess) {
        return repositories.get(mess.getRepo()).getTopics().get(mess.getQueue());
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
