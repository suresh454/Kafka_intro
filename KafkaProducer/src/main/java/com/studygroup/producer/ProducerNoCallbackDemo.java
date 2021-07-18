package com.studygroup.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerNoCallbackDemo {

    public static void main(String[] args) {
        {
            String topic = "new_demo_topic";
            KafkaProducer producer = getKafkaProducer();
            try {
                for(int i=0; i<10; i++){
                    RecordMetadata ack = (RecordMetadata) producer.send(new ProducerRecord(topic, "key"+i, "new Message all"+i)).get();
                    System.out.println(" Offset = " + ack.offset());
                    System.out.println(" Partition = " + ack.partition());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            /*
             Acks = 0
             ---------
             Offset = -1
             Partition = 1
             Posted to producer

             Acks = 1
             ---------
              Offset = 11
              Partition = 1
              Posted to producer

              Acks = all
              ----------
                Offset = 12
                Partition = 1
                Posted to producer
             */

            System.out.print("Posted to producer");
            producer.close();

        }
    }

    public static KafkaProducer<String,String> getKafkaProducer(){

        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"HelloProducer");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.ACKS_CONFIG,"1");
        return new KafkaProducer(properties);
    }
}
