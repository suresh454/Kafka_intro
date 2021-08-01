package com.studygroup.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

public class CustomPartitionDemo {


    public static void main(String[] args) {

        String topic = "demo_topic";
        KafkaProducer producer = getKafkaProducer();
        try {

                    IntStream.range(0,10).forEach((i) -> {
                        try {
                           final RecordMetadata record = (RecordMetadata) producer.send(new ProducerRecord(topic, i, "new Message - "+i)).get();
                            System.out.println("record = " + record.toString());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    });



        }finally {
            producer.close();
        }

        System.out.print("Posted to producer");


    }

    public static KafkaProducer<String,String> getKafkaProducer(){

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,CustomPartition.class.getName());
        return new KafkaProducer(properties);
    }
}
