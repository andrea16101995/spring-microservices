package it.javaboss.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.javaboss.AndConditionProfile;
import it.javaboss.controller.bean.RandomOperator;
import it.javaboss.service.OperatorGeneratorService;

@Service
@Profile({"eureka","!ribbon"})
@Conditional(AndConditionProfile.class)
public class OperatorGeneratorEurekaServiceImpl implements OperatorGeneratorService {
	
	@Autowired
	private RestTemplate rest;

	@Autowired
    private DiscoveryClient discoveryClient;
	
	@Value("${opertorGeneratorServiceId}")
	String opertorGeneratorServiceId;
	
	@PostConstruct
	public void init() {
		System.out.println(this.getClass().getName() + " active!!!");
	}
	
	public String getOperator() {
		String opertorGeneratorUrl = discoveryClient.getInstances(opertorGeneratorServiceId).get(0).getUri().toString();
		return rest.getForEntity(opertorGeneratorUrl, RandomOperator.class).getBody().getValue();
	}
}
