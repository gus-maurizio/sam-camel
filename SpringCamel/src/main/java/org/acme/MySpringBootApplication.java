package org.acme;

import org.apache.camel.CamelContext;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySpringBootApplication {

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) {
        SpringApplication
        	.run(MySpringBootApplication.class, args)
	        .getBean(CamelContext.class)
	        .createProducerTemplate()
    	    .sendBody("direct:start", "hello me to direct");
    }

}
