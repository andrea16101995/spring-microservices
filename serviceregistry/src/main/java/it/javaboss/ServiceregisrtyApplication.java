package it.javaboss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceregisrtyApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceregisrtyApplication.class, args);
	}
}
