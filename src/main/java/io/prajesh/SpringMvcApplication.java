package io.prajesh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class SpringMvcApplication {
  private static ApplicationContext ctx;

  public static void main(String[] args) {
    ctx = SpringApplication.run(SpringMvcApplication.class, args);
  }

  private static void viewLoadedBeans() {
    log.info("Beans*******");
    log.info("Total beans: " + ctx.getBeanDefinitionCount());
    for (String bean : ctx.getBeanDefinitionNames()) {
      log.info("Bean -> " + bean);
    }
  }
}
