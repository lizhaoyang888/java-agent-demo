package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// -javaagent:C:\GitProjects\java-agent-demo\agent-demo\target\agent-demo-0.0.1-SNAPSHOT.jar=testargs

@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

}
