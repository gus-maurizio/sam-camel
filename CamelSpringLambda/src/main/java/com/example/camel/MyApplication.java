package com.example.camel;

import org.apache.camel.CamelContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {

	public static void main(String[] args) {
		SpringApplication
			.run(MyApplication.class)
		    .getBean(CamelContext.class)
		    .createProducerTemplate()
		    .sendBody("direct:start", "");
	}
}