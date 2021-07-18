package com.studygroup.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {

    public static void main(String[] args) {

        String topic = "demo_topic";
//        KafkaProducer producer = getKafkaProducer();
//       producer.send(new ProducerRecord( topic, "4", "Demo"));
        KafkaProducer<String,String> kafkawriter= getKafkaProducer();
        ProducerRecord<String,String> msg=new ProducerRecord<>(topic,"ImKey1","Demo");
        kafkawriter.send(msg);
       System.out.print("Posted to producer");
        kafkawriter.close();


    }

    public static KafkaProducer<String,String> getKafkaProducer(){

        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"HelloProducer");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return new KafkaProducer(properties);
    }
}
