package com.studygroup.producer;

import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;

public class CustomPartition extends DefaultPartitioner {

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        return (Integer)key % cluster.partitionCountForTopic(topic);
        /*
        * 0 - 0
        * 1 - 1
        * 2 - 2
        * 3 - 0
        * 4 - 1
        * 5 - 2
        * 6 - 0
        * 7 - 1
        * 8 - 2
        * 9 - 0
         */
    }
}
