package it.javaboss;

import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;

public class RibbonConfiguration {
	@Bean
	public IPing ribbonPing(IClientConfig config) {
		return new PingUrl();
	}
}
