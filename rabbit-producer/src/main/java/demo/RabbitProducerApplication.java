package demo;

import demo.entity.ChatMessage;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableRabbit
public class RabbitProducerApplication{

    public static final String EXCHANGE_NAME = "flag2020";

    public static final String CHRIS_QUEUE = "CHRIS_QUEUE";
    public static final String TRUMP_QUEUE = "TRUMP_QUEUE";
    public static final String OBAMA_QUEUE = "OBAMA_QUEUE";

    public static final String ROUTING_KEY = "msg";

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue chrisQueue() {
        return new Queue(CHRIS_QUEUE);
    }

    @Bean
    public Queue trumpQueue() {
        return new Queue(TRUMP_QUEUE);
    }

    @Bean
    public Queue obamaQueue() {
        return new Queue(OBAMA_QUEUE);
    }

    @Bean
    public Binding chrisToExchangeBinding() {
        return BindingBuilder.bind(chrisQueue()).to(appExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding trumpToExchangeBinding() {
        return BindingBuilder.bind(trumpQueue()).to(appExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding obamaToExchangeBinding() {
        return BindingBuilder.bind(obamaQueue()).to(appExchange()).with(ROUTING_KEY);
    }

    @Autowired
    private Receiver receiver;

    public static void main(String[] args) {
        SpringApplication.run(RabbitProducerApplication.class, args);
    }

}

@Service
class Receiver {

    @RabbitListener(queues = RabbitProducerApplication.CHRIS_QUEUE)
    public void chrisReceiveMessage(final ChatMessage chatMessage) {
        System.out.println("Received message from CHRIS" + chatMessage);
    }

    @RabbitListener(queues = RabbitProducerApplication.TRUMP_QUEUE)
    public void trumpReceiveMessage(final ChatMessage chatMessage) {
        System.out.println("Received message from TRUMP" + chatMessage);
    }

    @RabbitListener(queues = RabbitProducerApplication.OBAMA_QUEUE)
    public void obamaReceiveMessage(final ChatMessage chatMessage) {
        System.out.println("Received message from OBAMA" + chatMessage);
    }
}
