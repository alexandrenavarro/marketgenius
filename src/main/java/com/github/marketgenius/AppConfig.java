package com.github.marketgenius;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marketgenius.ws.FeedResource;

@Configuration
public class AppConfig {
	
	@Bean
	public FeedResource RestMvcFeedResource() {
		return new com.github.marketgenius.ws.restmvc.RestMvcFeedResource();
	}
	
}
