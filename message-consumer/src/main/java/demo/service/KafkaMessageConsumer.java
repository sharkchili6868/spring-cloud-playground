package demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumer {

    @KafkaListener(topics = "message")
    public void listenMessage(String message) {
        System.out.println("Below message is received from configured topic");
        System.out.println(message);
    }
}
