package com.fynesy;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class PreFilter extends ZuulFilter {
	@Value( "${zuul.routes.primary.url}" )
	private String targetUrl;

	@Override
	public String filterType() {
		return "pre";
	}
	
	@Override
	public int filterOrder() {
		return 6;
	}
	
	@Override
	public boolean shouldFilter() {
		return true;
	}
	
	@Override
	public Object run() {
		
		
		RequestContext ctx = RequestContext.getCurrentContext();
		//String host = ctx.getRouteHost().toString();

		// Get target path (without leading /)
		String path = ctx.getRequest().getRequestURI().substring(1);

		// Get path prefix (top level context expected to be instance ordinal)
		String pathPrefix = "";
		if (path.indexOf("/")> -1)
			pathPrefix = path.substring(0,path.indexOf("/"));
		else
			pathPrefix = path;
		
		// Get path suffix (ie path stripped of prefix)
		String pathSuffix = path.substring(pathPrefix.length());

		// Inject prefix (ie instance ordinal) with period as target hostname prefix
		StringBuilder sb = new StringBuilder(targetUrl);
		sb.insert(targetUrl.indexOf("//")+2,pathPrefix+".");
		String newTargetUrl = sb.toString();
		
		// Override target hostname to have instance ordinal prefix
		// Strip prefix from path
		try {
			ctx.setRouteHost(new URL(newTargetUrl));
			ctx.set("requestURI", pathSuffix);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
