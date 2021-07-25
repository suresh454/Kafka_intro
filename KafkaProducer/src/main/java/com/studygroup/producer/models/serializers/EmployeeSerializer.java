package com.studygroup.producer.models.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studygroup.producer.models.Employee;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;


import java.util.Map;

public class EmployeeSerializer implements Serializer {

   private ObjectMapper objectMapper = null;

    @Override
    public void configure(Map configs, boolean isKey) {
        objectMapper = new ObjectMapper();
    }


    @Override
    public byte[] serialize(String topic, Object data) {
        if (! (data instanceof Employee)) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON message", e);
        }
    }


    @Override
    public void close() {

    }
}
