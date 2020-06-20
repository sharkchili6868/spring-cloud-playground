package demo.controller;

import demo.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public KafkaTemplate<String, String> getKafkaTemplate() {
        return kafkaTemplate;
    }

    @PostMapping("/produce")
    public Message produceMessage(@RequestBody Message message) {
        System.out.println("Producing new Message.....");
        System.out.println(message.toString());
        getKafkaTemplate().send("message", message.toString());
        System.out.println("Successfully publish message to Kafka");
        return message;
    }
}
