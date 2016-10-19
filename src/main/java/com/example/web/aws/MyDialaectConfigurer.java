package com.example.web.aws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.web.aws.thymeleaf.dialect.MyDialect;
import com.example.web.aws.thymeleaf.dialect.PersonaDialect;

@Configuration
public class MyDialaectConfigurer {

	@Bean
	PersonaDialect personaDialect() {
		return new PersonaDialect();
	}

	@Bean
	MyDialect myDialect() {
		return new MyDialect();
	}

}
