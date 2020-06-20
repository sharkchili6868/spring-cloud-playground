package demo.controller;

import demo.entity.Message;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

@RestController
public class ConsumerRestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consume/{topic}", method = {RequestMethod.GET})
    @ResponseBody
    public String consumeMessage(@PathVariable String topic) {

        ConsumerFactory<String, Object> consumerFactory = getConsumerFactoryInstance();

        Consumer<String, Object> consumer = consumerFactory.createConsumer();

        consumer.subscribe(Collections.singletonList("sampleTopic"));

        // poll messages from last 10 days
        ConsumerRecords<String, Object> consumerRecords = consumer.poll(1);

        // print on console or send back as a string/json. Feel free to change controller function implementation for ResponseBody
        consumerRecords.forEach(action -> {
            System.out.println(action.value());
        });

        return "success";
    }

    public ConsumerFactory<String, Object> getConsumerFactoryInstance() {
        Map<String, Object> configs = new java.util.HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        configs.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
        configs.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 1000);
        ConsumerFactory<String, Object> consumerFactory = new DefaultKafkaConsumerFactory<>(configs);
        return consumerFactory;
    }
}
