package com.mmail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mmail.*")
@SpringBootApplication
public class MmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MmailApplication.class, args);
    }
}
