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
public class NumberGeneratorController {
	
	private static Logger logger = LoggerFactory.getLogger( NumberGeneratorController.class );
	
	Random rand = new Random();
	
	@Value("${maxint}")
	Integer maxint;
	
	@RequestMapping
	public ResponseEntity<RandomInteger> getNumber(){
		RandomInteger value = new RandomInteger( rand.nextInt( maxint ) + 1 );
		
		logger.info( "Generated number " +  value.getValue() );
		
		return new ResponseEntity<RandomInteger>( value, HttpStatus.OK );
	}
}
