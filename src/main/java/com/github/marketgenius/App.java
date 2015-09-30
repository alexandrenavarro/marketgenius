/**
 * 
 */
package com.github.marketgenius;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author smillar
 *
 */
@EnableAutoConfiguration
@Configuration
@Import(AppConfig.class)
public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.info("The application is starting ...");
		final long start = System.currentTimeMillis(); 
		SpringApplication.run(App.class, args);
		final long stop = System.currentTimeMillis();
		LOGGER.info("The application is started in {} ms.", stop - start);
		
	}

}
