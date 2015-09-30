package com.github.marketgenius;

import com.github.marketgenius.ws.restmvc.RestMvcFeedResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebMvc
public class AppConfig {


	/**
	 * @return
	 */
	@Bean
	RestMvcFeedResource restMvcFeedResource() {
		return new RestMvcFeedResource();
	}
}
