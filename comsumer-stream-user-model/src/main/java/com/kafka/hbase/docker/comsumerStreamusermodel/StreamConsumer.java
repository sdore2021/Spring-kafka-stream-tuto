package com.kafka.hbase.docker.comsumerStreamusermodel;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StreamConsumer {
/*
	@KafkaListener(topics = {"dore1"}, groupId = "spring-boot-kafka")
	public void consume(ConsumerRecord<String, UserModel> record) {
		 System.out.println("received value = " + record.value().toString() + " key = " + record.key());
	}
	*/
}
