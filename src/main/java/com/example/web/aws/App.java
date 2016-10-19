package com.example.web.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ForwardedHeaderFilter;

@SpringBootApplication
public class App {

	public static void main( String[] args ) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public ForwardedHeaderFilter forwardedHeaderFilter() { // FilterをBean登録する
		ForwardedHeaderFilter filter = new ForwardedHeaderFilter();
		//    FilterConfig filterConfig = null;
		//    filter.init(filterConfig);
		return filter;
	}

}
