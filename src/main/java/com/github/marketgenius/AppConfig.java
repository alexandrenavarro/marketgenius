package com.github.marketgenius;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marketgenius.ws.restmvc.RestMvcFeedResource;

@Configuration
public class AppConfig {


	/**
	 * @return
	 */
	@Bean
	RestMvcFeedResource restMvcFeedResource() {
		return new RestMvcFeedResource();
	}
}
