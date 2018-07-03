package it.javaboss;


import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class OperatorGeneratorController {
	
	private static Logger logger = LoggerFactory.getLogger( OperatorGeneratorController.class );
	
	Random rand = new Random();

	@Value("${operators}")
	String[] operators;
	
	@RequestMapping
	public ResponseEntity<RandomOperator> getOperator(){
		int index = rand.nextInt( operators.length );
		RandomOperator operator = new RandomOperator( operators[ index ] );
		
		logger.info( "Generated operator " +  operator.getValue() );
		
		return new ResponseEntity<RandomOperator>( operator, HttpStatus.OK );
	}
}
