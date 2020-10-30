package com.garbarino.cart.process.test.task;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.garbarino.cart.process.task.Application;
import com.garbarino.cart.process.task.CartProcessScheduledTask;
import junit.framework.TestCase;

@Ignore
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("dev")
@SpringBootTest
@Transactional
public class CartProcessScheduledTaskTest extends TestCase {

  @Autowired
  private CartProcessScheduledTask task;
	
  @Test
  public void runTask() {
	task.cartProcess();  
  }

}