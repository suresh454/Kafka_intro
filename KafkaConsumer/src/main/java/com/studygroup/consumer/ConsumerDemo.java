package com.studygroup.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo {

    public static void main(String[] args) {
        String topic = "demo_topic";
        KafkaConsumer consumer = getConsumer();
        consumer.subscribe(Arrays.asList(topic));

        try{
            System.out.println("Starting the consumer for demo_topic...");
            while(true){
                ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<Integer,String> record : records) {
                    System.out.println("record = " + record.toString());
                }
            }
        }finally {
            consumer.close();
        }
    }

    private static KafkaConsumer getConsumer(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"consumer-group");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        return new KafkaConsumer(properties);
    }
}
