package com.garbarino.cart.process.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.garbarino.cart"})
@EnableSpringDataWebSupport
@EnableJpaAuditing
@EntityScan("com.garbarino.cart.process.task.model")
@EnableJpaRepositories("com.garbarino.cart.process.task.repository")
public class Application {

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
