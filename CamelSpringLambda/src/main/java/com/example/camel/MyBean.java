package com.example.camel;

import org.apache.camel.main.Main;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Produce;
import org.apache.camel.InOnly;

public class MyBean {

    private String hi;
    private String bye;

    // @Produce("direct:lambda")
    // protected ProducerTemplate producer;


    public MyBean(String hi, String bye) {
        this.hi = hi;
        this.bye = bye;
    }

    public String hello() {
        return hi + " how are you?";
    }

    public String bye() {
        return bye + " World";
    }

    // @InOnly
    // public void sendRecord() {
    //     // lets send a message
    //     producer.sendBody("James");
    // }

}

