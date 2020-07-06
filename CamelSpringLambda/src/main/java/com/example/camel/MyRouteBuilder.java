package com.example.camel;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:healthcheck?period={{myPeriod}}")
            .bean("myBean", "hello")
            .log("${body}")
            .bean("myBean", "bye")
            .to("log:healthcheck?level=INFO&showAll=true");

        from("aws-s3://{{camel.bucket}}?amazonS3Client=#s3Client")
            .routeId("s3read")
            .streamCaching()
            .to("log:s3?level=INFO&showAll=true");

        from("direct:start")
            .routeId("lambda")
            .streamCaching()
            .to("log:lambda?level=INFO&showAll=true");


    }
}
