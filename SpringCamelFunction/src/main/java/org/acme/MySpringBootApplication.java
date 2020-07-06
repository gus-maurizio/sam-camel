package org.acme;

import org.apache.camel.CamelContext;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// public class MySpringBootApplication  implements CommandLineRunner {
public class MySpringBootApplication   {
    private static final Logger logger = LoggerFactory.getLogger(MySpringBootApplication.class);

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

    // @Override
    // public void run(String... args) throws Exception {
    //     logger.info("Joining thread, you can press Ctrl+C to shutdown application");
    //     Thread.currentThread().join();
    // }

}
