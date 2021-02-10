package com.kafka.ConsumerProducer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafka.ConsumerProducer.model.Employee;

@EnableKafka
@Configuration
public class ConsumerConfiguration {

	@Bean
	public ConsumerFactory<String, Employee> EmployeeConsumerFactory(){
		
		Map<String, Object> config = new HashMap<String, Object>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_json");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<String, Employee>(config, new StringDeserializer(), new JsonDeserializer<>(Employee.class));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Employee> EmployeekafkaListenerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Employee> factory = new ConcurrentKafkaListenerContainerFactory<String, Employee>();
		factory.setConsumerFactory(EmployeeConsumerFactory());
		return factory;
	}
	
}
