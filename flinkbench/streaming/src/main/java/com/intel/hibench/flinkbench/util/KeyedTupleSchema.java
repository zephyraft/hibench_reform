package com.intel.hibench.flinkbench.util;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.typeutils.TupleTypeInfo;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.nio.charset.StandardCharsets;

public class KeyedTupleSchema implements KafkaDeserializationSchema<Tuple2<String, String>> {
    @Override
    public boolean isEndOfStream(Tuple2<String, String> nextElement) {
        return false;
    }

    @Override
    public Tuple2<String, String> deserialize(ConsumerRecord<byte[], byte[]> record) throws Exception {
        return new Tuple2<>(new String(record.key(), StandardCharsets.UTF_8), new String(record.value(), StandardCharsets.UTF_8));
    }

    @Override
    public TypeInformation<Tuple2<String, String>> getProducedType() {
        return new TupleTypeInfo<>(TypeExtractor.createTypeInfo(String.class), TypeExtractor.createTypeInfo(String.class));
    }

}

