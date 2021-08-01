package com.studygroup.producer;

import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerAvroSerializer {



    public static void main(String[] args) {
        org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":" +
                "\"record\",\"name\":\"Employee\",\"namespace\":\"kafka.studygroup\",\"fields\":" +
                "[{\"name\":\"name\",\"type\":[\"null\",\"string\"]},{\"name\":\"department\",\"type\":[\"null\"," +
                "\"string\"]},{\"name\":\"id\",\"type\":[\"null\",\"int\"]}" +
                "]}");

        GenericRecord record = new GenericData.Record(SCHEMA$);

        record.put("name","Suresh");
        record.put("id",1);
        record.put("department","Vamsi");

         org.apache.avro.io.DatumWriter
                WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

         ByteArrayOutputStream out =new ByteArrayOutputStream();
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out,null);

        try {
            WRITER$.write(record,encoder);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                encoder.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        byte[] byteArray = out.toByteArray();

        String topic = "new_demo_topic";
        KafkaProducer producer = getKafkaProducer();
        try {
            RecordMetadata resp = (RecordMetadata) producer.send(new ProducerRecord(topic, "1", byteArray)).get();
            System.out.println("Response = " + resp.toString());
            producer.flush();
            producer.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    public static KafkaProducer<String,String> getKafkaProducer(){

        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"HelloProducer");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        return new KafkaProducer(properties);
    }
}
