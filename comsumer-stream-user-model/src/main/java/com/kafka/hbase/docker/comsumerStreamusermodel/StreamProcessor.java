package com.kafka.hbase.docker.comsumerStreamusermodel;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;


@Component
public class StreamProcessor {
	
	@Autowired
	public void process(StreamsBuilder builder) {
		
		final Serde<String> stringSerde = Serdes.String();
        
        final Serde<UserModel> userModelSerde = Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(UserModel.class));

        
        KStream<String, UserModel> streams = builder.stream("dore-topic", Consumed.with(stringSerde, userModelSerde));
        KStream<String, Object> reduit = streams.mapValues(record -> record.getFirstnane());
        
        System.out.println("received from procces:"+ streams);
        
        reduit.to("sam-topic-1");
	}
	
	final Serde<UserModel> userModelSerde = Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(UserModel.class));
	
	@KafkaListener(topics = {"sam-topic-1"}, groupId = "spring-boot-kafka")
	public void consume(ConsumerRecord<String, Object> record) {
		 System.out.println("received kafkalistener = " + record.value().toString() + " key = " + record.key());
	}
	

}
