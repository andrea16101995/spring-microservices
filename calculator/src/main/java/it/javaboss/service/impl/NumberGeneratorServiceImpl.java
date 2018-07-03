package it.javaboss.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.javaboss.AndConditionProfile;
import it.javaboss.controller.bean.RandomInteger;
import it.javaboss.service.NumberGeneratorService;

@Service
@Profile({"!eureka","!ribbon"})
@Conditional(AndConditionProfile.class)
public class NumberGeneratorServiceImpl implements NumberGeneratorService {
	
	@Autowired
	private RestTemplate rest;

	@Value("${numberGeneratorUrl}")
	String numberGeneratorUrl;
	
	@PostConstruct
	public void init() {
		System.out.println(this.getClass().getName() + " active!!!");
	}
	
	public Integer getNumber() {
		return rest.getForEntity(numberGeneratorUrl, RandomInteger.class).getBody().getValue();
	}
}
