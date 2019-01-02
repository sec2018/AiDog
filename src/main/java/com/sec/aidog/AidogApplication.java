package com.sec.aidog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

//@SpringBootApplication
//@EnableSwagger2
//public class AidogApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(AidogApplication.class, args);
//	}
//}


//for war
@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
public class AidogApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AidogApplication.class);
	}
	public static void main( String[] args ){
		SpringApplication.run(AidogApplication.class, args);
	}
}
