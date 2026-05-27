package com.ebjavademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = {"com.ebjavademo", "com.demo"})
@ServletComponentScan(basePackages = "com.demo")
public class EbJavaDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EbJavaDemoApplication.class, args);
    }
}

