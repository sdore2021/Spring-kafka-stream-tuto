package com.kafka.hbase.docker.comsumerStreamusermodel;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
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
import org.springframework.kafka.support.serializer.JsonSerializer;




@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamsConfig {

	@Bean(name=KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	public KafkaStreamsConfiguration kStreamsConfigs() {
		
		Map<String, Object> config = new HashMap<>();
		
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "applicationid" );
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerializer.class); //JSONSerde.class
		config.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 1000L);
		
		//config.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, JsonTimestampExtractor.class);
		// setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		return new KafkaStreamsConfiguration(config);
		//props.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, Foo.class);
	}
	
	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_BUILDER_BEAN_NAME)
    public StreamsBuilderFactoryBean defaultKafkaStreamsBuilder() {
        return new StreamsBuilderFactoryBean(kStreamsConfigs(), new CleanupConfig(true, true));
    }
	
}
