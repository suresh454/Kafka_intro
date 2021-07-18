package com.studygroup.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerAsyncDemo {

    public static void main(String[] args) {
        String topic = "demo_topic";
        KafkaProducer producer = getKafkaProducer();
        try {
            producer.send(new ProducerRecord(topic, "4", "new Message"), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {

                    if (e== null) {
                        System.out.println("Successfully received the details as: \n" +
                                "Topic:" + recordMetadata.topic() + "\n" +
                                "Partition:" + recordMetadata.partition() + "\n" +
                                "Offset" + recordMetadata.offset() + "\n" +
                                "Timestamp" + recordMetadata.timestamp());
                    }

                    else {
                        System.out.println("Can't produce,getting error"+ e.getMessage());

                    }
                }
            }).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.print("Posted to producer");
        producer.close();

    }

    public static KafkaProducer<String,String> getKafkaProducer(){

        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"HelloProducer");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        return new KafkaProducer(properties);
    }
}

