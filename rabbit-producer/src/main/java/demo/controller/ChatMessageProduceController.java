package demo.controller;

import demo.RabbitProducerApplication;
import demo.entity.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class ChatMessageProduceController {

    private static final Logger log = LoggerFactory.getLogger(ChatMessageProduceController.class);

    private final RabbitTemplate rabbitTemplate;


    public ChatMessageProduceController(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/chris")
    public String chrisSendMessage(@RequestBody ChatMessage chatMessage) {
        log.info("Chris Sending message...");
        rabbitTemplate.convertAndSend(RabbitProducerApplication.EXCHANGE_NAME,
                RabbitProducerApplication.ROUTING_KEY, chatMessage);
        return chatMessage.toString();
    }

    @PostMapping("/trump")
    public String trumpSendMessage(@RequestBody ChatMessage chatMessage) {
        log.info("Trump Sending message...");
        rabbitTemplate.convertAndSend(RabbitProducerApplication.EXCHANGE_NAME,
                RabbitProducerApplication.ROUTING_KEY, chatMessage);
        return chatMessage.toString();
    }

    @PostMapping("/obama")
    public String obamaSendMessage(@RequestBody ChatMessage chatMessage) {
        log.info("Obama Sending message...");
        rabbitTemplate.convertAndSend(RabbitProducerApplication.EXCHANGE_NAME,
                RabbitProducerApplication.ROUTING_KEY, chatMessage);
        return chatMessage.toString();
    }
}
