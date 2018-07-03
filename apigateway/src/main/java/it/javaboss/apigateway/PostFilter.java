package it.javaboss.apigateway;

import java.util.List;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PostFilter extends ZuulFilter {
	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 999999;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object run() {
		final List<String> routingDebug = (List<String>) RequestContext.getCurrentContext().get("routingDebug");
		routingDebug.forEach(System.out::println);
		return null;
	}
}
