package it.javaboss.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.javaboss.controller.bean.Solution;
import it.javaboss.service.NumberGeneratorService;
import it.javaboss.service.OperatorGeneraatorService;

@RefreshScope
@RestController
public class CalculatorController {
	
	private static Logger logger = LoggerFactory.getLogger( CalculatorController.class );
	
	@Autowired
	OperatorGeneraatorService operatorGeneraatorService;
	
	@Autowired
	NumberGeneratorService numberGeneratorService;
	
	private ExpressionParser parser = new SpelExpressionParser();
	
	@RequestMapping
	public Solution calculate() {
		
		logger.info( "Calling services to get data" );
		
		String expression = numberGeneratorService.getNumber() + " " + operatorGeneraatorService.getOperator() + " " + numberGeneratorService.getNumber();
		
		logger.info( "Parsing exp: " + expression );
		
		Expression exp = parser.parseExpression( expression );
		
		Solution solution = new Solution();
		solution.setExpression( expression );
		solution.setValue( exp.getValue().toString() );
		
		return solution ;
	}

}
