package com.studygroup.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {

    public static void main(String[] args) {

        String topic = "demo_topic";
//        KafkaProducer producer = getKafkaProducer();
//       producer.send(new ProducerRecord( topic, "4", "Demo"));
        KafkaProducer<Integer,String> kafkawriter= getKafkaProducer();
        ProducerRecord<Integer,String> msg=new ProducerRecord<>(topic,5,"Demo - 1");
        kafkawriter.send(msg);
       System.out.print("Posted to producer");
        kafkawriter.close();


    }

    public static KafkaProducer<Integer,String> getKafkaProducer(){

        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"HelloProducer");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        return new KafkaProducer(properties);
    }
}
