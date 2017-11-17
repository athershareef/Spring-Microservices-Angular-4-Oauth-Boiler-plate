package com.gift.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfiguration. Creates API Documentation
 * {@link:http://localhost:8080/swagger-ui.htm}
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	/**
	 * API Documentation: visit http://localhost:8080/swagger-ui.html for UI
	 * visit http://localhost:8080/v2/api-docs for API Document
	 *
	 * @return the docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2);
	}
}
