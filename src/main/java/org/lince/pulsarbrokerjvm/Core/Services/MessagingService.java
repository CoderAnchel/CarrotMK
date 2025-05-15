package org.lince.pulsarbrokerjvm.Core.Services;

import org.lince.pulsarbrokerjvm.Configuration.Entities.QueueConfig;
import org.lince.pulsarbrokerjvm.Configuration.Entities.RepositoryConfig;
import org.lince.pulsarbrokerjvm.Core.Entities.Message;
import org.lince.pulsarbrokerjvm.Core.Entities.Queue;
import org.lince.pulsarbrokerjvm.Core.Entities.Repository;
import org.lince.pulsarbrokerjvm.Core.PulsarBroker;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class MessagingService {

    public static void filterMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        System.out.println("payload: "+payload);

        Message mess = new Message(payload, session);
        System.out.println("Message received: " + mess);
        route(mess);

        // Echo the message back
        session.sendMessage(new TextMessage("Echo: " + payload));
    }

    public static void route(Message mess) {
        switch (mess.getContentType()) {
            case "TEXT/JSON":
                System.out.println("Routable: "+ PulsarBroker.validateRouting(mess));
                break;
            case "QUEUE_REGISTRATION":
                System.out.println("Routable: "+ PulsarBroker.validateRouting(mess));
                break;
            case "QUEUE_CREATION":
                QueueConfig queueConfig = new QueueConfig(mess.getPayload());
                Queue queue = new Queue(queueConfig.getName(), queueConfig);
                PulsarBroker.addQueue(mess,  queue);
                System.out.println("Queue config readed: "+queueConfig);
                break;
            case "REPO_CREATION":
                RepositoryConfig repositoryConfig = new RepositoryConfig(mess.getPayload());
                System.out.println("Repo config readed: "+repositoryConfig);
                PulsarBroker.addRepository(new Repository(repositoryConfig.getName(), repositoryConfig));
                break;
        }
    }
}
