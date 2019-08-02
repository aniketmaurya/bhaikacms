package com.cmssystem.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuditApplication {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        SpringApplication.run(AuditApplication.class, args);
    }

}
