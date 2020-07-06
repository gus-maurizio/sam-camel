package org.acme;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
public class App implements RequestHandler<Object, Object> {

    private static final Logger logger = LoggerFactory.getLogger(App.class);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public App() {
    }

    @Override
    public Object handleRequest(final Object input, final Context context) {
        logger.info("input: {}", gson.toJson(input));
        logger.info("context: {}", gson.toJson(context));
        Object callCamel = startCamel();
        return input;
    }

    public Object startCamel() {
        logger.info("Starting Camel Configuration");
        SpringApplication
            .run(MySpringBootApplication.class)
            .getBean(CamelContext.class)
            .createProducerTemplate()
            .sendBody("direct:start", "hello me to direct");
        logger.info("Ending Camel");
        return null;
    }

}
