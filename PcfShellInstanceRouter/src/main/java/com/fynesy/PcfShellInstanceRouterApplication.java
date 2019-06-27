package com.fynesy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
public class PcfShellInstanceRouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcfShellInstanceRouterApplication.class, args);
	}
	
	
	@Bean
	public PreFilter routeFilter() {
		return new PreFilter();
	}
	
	@Controller
	class UiController {
	    @GetMapping(value = "/web")
	    public String index() {
	        return "index.html";
	    }
	}
	
}
