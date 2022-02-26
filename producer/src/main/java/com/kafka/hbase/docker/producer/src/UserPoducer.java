package com.kafka.hbase.docker.producer.src;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPoducer {
	
	private static final String TOPIC_KAFKA = "sam-topic-1";
	
	@Autowired
	KafkaTemplate<String, UserModel> kafkatemplate;
	
	@PostMapping("/post")
	public String sendMessage(@RequestBody UserModel message){
		
		UserModel user = new UserModel(message.firstnane, message.lastname, message.age);
		
		kafkatemplate.send(TOPIC_KAFKA, user);
		return "pushish message successful";
	}

	@GetMapping("/post")
	public String sendMessage1(){
		
		return "it works fine !";
	}
	
	@KafkaListener(
			topics = "sam-topic-1",
			containerFactory="userModelkafkaListerContainerFactory")
	public void listen(UserModel message) {
		System.out.println("Received Message : " + message.toString());
	}
	
	
}
