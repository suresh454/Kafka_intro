package com.studygroup.producer;


import com.studygroup.producer.models.Employee;
import com.studygroup.producer.models.serializers.EmployeeSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerSerializer{

    public static void main(String[] args) {
        String topic = "new_demo_topic";
        KafkaProducer producer = getKafkaProducer();
        try {
            for(int i=0; i<10; i++){
                Employee employee = createEmployee(i);
                Future<RecordMetadata> ackFuture =  producer.send(new ProducerRecord(topic, i, employee));
                RecordMetadata ack = ackFuture.get();
                System.out.println(" Offset = " + ack.offset());
                System.out.println(" Partition = " + ack.partition());
                System.out.println("Topic = " + ack.toString());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static Employee createEmployee(int i) {
        return new Employee("Stanley",i,"IT");

    }

    public static KafkaProducer<String,String> getKafkaProducer(){

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EmployeeSerializer.class.getName());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.ACKS_CONFIG,"1");
        return new KafkaProducer(properties);
    }
}
