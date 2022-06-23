package com.example.mileageapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MileageApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(MileageApiApplication.class, args);
  }
}
