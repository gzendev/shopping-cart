package com.garbarino.cart.process.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.garbarino.cart.process.task.exception.CartException;
import com.garbarino.cart.process.task.service.CartService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class CartProcessScheduledTask {

  @Autowired
  private CartService cartService;

  @Scheduled(cron = "0 */10 * ? * *") 
  public void cartProcess() {
    log.info("cartProcess(): {}",
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    log.info("Cart Processing...");
    try {
    	cartService.process();
      log.info("Cart process: run successfully on {}", LocalDate.now());
    } catch (final CartException e) {
      log.info("Cart process: failed on {}, {}", LocalDate.now(), e.getMessage());
    }
  }

}
