package com.example.camel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.s3.S3Client;

import org.apache.camel.main.Main;
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
        Main main = new Main();
        logger.info("Starting Camel Configuration");
        main.configure().addConfigurationClass(MyConfiguration.class);
        // and add the routes (you can specify multiple classes)
        logger.info("Adding Routes");
        main.configure().addRoutesBuilder(MyRouteBuilder.class);
        // now keep the application running until the JVM is terminated (ctrl + c or sigterm)
        try {
            logger.info("Running Camel main");
            main.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Ending Camel");
        return null;
    }

}
