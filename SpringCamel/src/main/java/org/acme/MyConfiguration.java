package org.acme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.*;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.regions.Regions;


@Configuration
public class MyConfiguration {

    @Bean
    public static AmazonS3 s3Client() {
        return AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.US_EAST_1)
            .build();
    }


}
