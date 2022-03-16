package com.kafka.hbase.docker.comsumerstream;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StreamConsumer {

	@KafkaListener(topics = {"dore2"}, groupId = "spring-boot-kafka")
	public void consume(ConsumerRecord<String, Long> record) {
		 System.out.println("received = " + record.value() + " with key " + record.key());
	}
}
