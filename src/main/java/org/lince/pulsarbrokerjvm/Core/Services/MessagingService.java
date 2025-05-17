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

    public static void filterMessage(WebSocketSession session, TextMessage message) throws
            IOException, InstantiationException, IllegalAccessException {
        String payload = message.getPayload();
        System.out.println("payload: "+payload);

        Message mess = new Message(payload, session);
        System.out.println("Message received: " + mess);
        route(mess);
    }

    /*
{
            "producer_id":"id1",
        "content_type":"TEXT/JSON",
        "payload":{
                "name":"Coladegato",
                "description":"cola de gato"
        },
        "timestamp":"x",
        "repo":"REPOSITORY1",
        "topic":"Coladegato"
        }
     */

    //optimizar
    public static void route(Message mess) throws IOException, InstantiationException,
            IllegalAccessException {
        switch (mess.getContentType()) {
            case "TEXT/JSON":
                PulsarBroker.newMessage(mess);
                //System.out.println("Routable: "+ PulsarBroker.validateRouting(mess));
                break;
            case "QUEUE_REGISTRATION":
                PulsarBroker.addListener(mess.getProducerId(), mess);
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
            case "QUEUE_INFORMATION":

                break;
        }
    }
}

