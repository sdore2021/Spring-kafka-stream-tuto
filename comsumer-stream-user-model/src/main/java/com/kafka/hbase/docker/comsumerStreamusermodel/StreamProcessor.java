package com.kafka.hbase.docker.comsumerStreamusermodel;

import java.util.Arrays;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;

import org.apache.kafka.streams.kstream.KStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class StreamProcessor {
	
	@Autowired
	public void process(StreamsBuilder builder) {
		
		final Serde<String> stringSerde = Serdes.String();
        
        final Serializer<JsonNode> jsonSerializer = new JsonSerializer();
        final Deserializer<JsonNode> jsonDeserializer = new JsonDeserializer();
        final Serde<JsonNode> jsonSerde = Serdes.serdeFrom(jsonSerializer, jsonDeserializer);

        
        KStream<String, JsonNode> streams = builder.stream("dore1", Consumed.with(stringSerde, jsonSerde));
        
        System.out.println("received:"+ streams);
	}

}
