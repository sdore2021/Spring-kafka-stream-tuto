package com.kafka.hbase.docker.comsumerstream;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serdes;

import org.apache.kafka.streams.StreamsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.CleanupConfig;




@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamsConfig {

	@Bean(name=KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	public KafkaStreamsConfiguration kStreamsConfigs() {
		
		Map<String, Object> config = new HashMap<>();
		
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "applicationid" );
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName());
		config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName()); //JsonSerializer.class
		
		return new KafkaStreamsConfiguration(config);
		//props.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, Foo.class);
	}
	
	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_BUILDER_BEAN_NAME)
    public StreamsBuilderFactoryBean defaultKafkaStreamsBuilder() {
        return new StreamsBuilderFactoryBean(kStreamsConfigs(), new CleanupConfig(true, true));
    }
	
}
