package it.javaboss.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.javaboss.AndConditionProfile;
import it.javaboss.controller.bean.RandomOperator;
import it.javaboss.service.OperatorGeneraatorService;

@Service
@Profile({"!eureka","!ribbon"})
@Conditional(AndConditionProfile.class)
public class OperatorGeneraatorServiceImpl implements OperatorGeneraatorService {
	
	@Value("${opertorGeneratorUrl}")
	String opertorGeneratorUrl;
	
	@Autowired
	private RestTemplate rest;
	
	@PostConstruct
	public void init() {
		System.out.println(this.getClass().getName() + " active!!!");
	}

	public String getOperator() {
		return rest.getForEntity(opertorGeneratorUrl, RandomOperator.class).getBody().getValue();
	}
}
