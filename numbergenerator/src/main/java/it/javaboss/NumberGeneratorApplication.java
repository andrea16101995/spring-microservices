package it.javaboss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@EnableDiscoveryClient
@SpringBootApplication
public class NumberGeneratorApplication {
	public static void main( String[] args ) throws Exception {
        SpringApplication.run(NumberGeneratorApplication.class, args);
    }
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
