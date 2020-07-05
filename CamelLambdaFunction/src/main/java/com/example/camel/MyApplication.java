package com.example.camel;

import org.apache.camel.main.Main;

/**
 * Main class that boot the Camel application
 */
public final class MyApplication {

    private MyApplication() {
    }


    public static void main(String[] args) throws Exception {
        // use Camels Main class
        // Main main = new Main();
        App  myapp = new App();
        Object callCamel = myapp.startCamel();

        // lets use a configuration class (you can specify multiple classes)
        // (properties are automatic loaded from application.properties)
        // main.configure().addConfigurationClass(MyConfiguration.class);
        // and add the routes (you can specify multiple classes)
        // main.configure().addRoutesBuilder(MyRouteBuilder.class);
        // now keep the application running until the JVM is terminated (ctrl + c or sigterm)
        // main.run(args);
    }

}
