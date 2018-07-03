package it.javaboss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

@RibbonClient( name = "calculator", configuration = RibbonConfiguration.class) // For Ribbon
@EnableDiscoveryClient
@EnableCircuitBreaker // For Hystrix and Ribbon
@SpringBootApplication
public class CalculatorApplication {
	public static void main( String[] args ) throws Exception {
        SpringApplication.run(CalculatorApplication.class, args);
    }
	
	@Bean
	@Profile("!ribbon")
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}
	
	@LoadBalanced
	@Bean
	@Profile("ribbon")
	public RestTemplate restTemplateBalanced() {
		return new RestTemplateBuilder().build();
	}
	
	@Bean
	@Profile("ribbon")
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
