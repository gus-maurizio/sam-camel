package com.example.camel;

import org.apache.camel.BindToRegistry;
import org.apache.camel.PropertyInject;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.regions.Regions;


/**
 * Class to configure the Camel application.
 */
public class MyConfiguration {

    @BindToRegistry
    public MyBean myBean(@PropertyInject("hi") String hi, @PropertyInject("bye") String bye) {
        // this will create an instance of this bean with the name of the method (eg myBean)
        MyBean answer = new MyBean(hi, bye);
        return answer;
    }


    @BindToRegistry
    public static AmazonS3 s3Client() {
        return AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.US_EAST_1)
            .build();
    }


    public void configure() {
        // this method is optional and can be removed if no additional configuration is needed.
    }

}
