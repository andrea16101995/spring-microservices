package it.javaboss.apigateway;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class RouteFilter extends ZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger( RouteFilter.class );

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
	
	@Override
	public Object run() throws ZuulException {
		
		 RequestContext requestContext = RequestContext.getCurrentContext();
		 
		 HttpServletRequest request = requestContext.getRequest();
	 
	     String requestUrl = URI.create(request.getRequestURI()).getPath();
	     
	     logger.info( "New request for: " + requestUrl );
		
		return null;
	}
}
