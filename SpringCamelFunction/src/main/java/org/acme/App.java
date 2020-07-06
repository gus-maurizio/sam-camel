package org.acme;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.camel.CamelContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Handler for requests to Lambda function.
 */

@SpringBootApplication
public class App implements RequestHandler<Object, Object> {

    private static final Logger logger = LoggerFactory.getLogger(App.class);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public App() {
    }

    @Override
    public Object handleRequest(final Object input, final Context context) {
        logger.info("input: {}", gson.toJson(input));
        logger.info("context: {}", gson.toJson(context));
        Object callCamel = startCamel(input);
        return input;
    }

    public Object startCamel(Object input) {

        logger.info("Starting Camel Configuration");

        SpringApplication
            .run(MySpringBootApplication.class)
            .getBean(CamelContext.class)
            .createProducerTemplate()
            .sendBody("direct:start", input);

        Set<Thread> threads = Thread.getAllStackTraces().keySet();
        // wait for CamelMainRunController thread 
        Thread mainCamel = null;

        for (Thread t : threads) {
            String name = t.getName();
            Thread.State state = t.getState();
            int priority = t.getPriority();
            String type = t.isDaemon() ? "Daemon" : "Normal";
            // System.out.printf("%-20s \t %s \t %d \t %s\n", name, state, priority, type);
            // wait for CamelMainRunController thread 
            if (name.startsWith("CamelMain")) {
                mainCamel = t;
            }
            if (name.startsWith("Camel")) {
                logger.info(String.format("GOT CAMEL THREAD %-20s %s %d %s", name, state, priority, type));
            }
        }

        try {
            logger.info("WAIT for CAMEL");  
            // Thread.sleep(20000L);  
            mainCamel.join();
            logger.info("CAMEL ENDED"); 
        } catch(InterruptedException e) {  
            throw new RuntimeException("Thread interrupted..."+e);  
        }  
        // threads = Thread.getAllStackTraces().keySet();
         
        // for (Thread t : threads) {
        //     String name = t.getName();
        //     Thread.State state = t.getState();
        //     int priority = t.getPriority();
        //     String type = t.isDaemon() ? "Daemon" : "Normal";
        //     System.out.printf("%-20s \t %s \t %d \t %s\n", name, state, priority, type);
        // }


        logger.info("Ending Camel lambda");
        return null;
    }

}
