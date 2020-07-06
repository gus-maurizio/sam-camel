package org.acme;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("timer:hello?period={{timer.period}}").routeId("hello")
            .transform().method("myBean", "saySomething")
            .filter(simple("${body} contains 'foo'"))
                .to("log:foo")
            .end()
            .to("log:healthcheck?level=INFO&showAll=true");
            // .to("stream:out");

        from("aws-s3://{{camel.bucket}}?amazonS3Client=#s3Client")
            .routeId("s3read")
            .streamCaching()
            .to("log:s3?level=INFO&showAll=true");

        from("direct:start")
            .id("mydirect")
            .to("log:mydirect?level=INFO&showAll=true");

            
    }

}
