package hello.proxy.postproccesor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by park on 2022/05/05.
 */

public class BasicTest {

  @Test
  public void basicConfig() {
    //given
    ApplicationContext context = new AnnotationConfigApplicationContext(basicConfig.class);
    A a = context.getBean("beanA",A.class);
    a.helloA();

    //when

    //then
    Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(B.class));
  }

  @Slf4j
  @Configuration
  static class basicConfig {

    @Bean(name = "beanA")
    public A a() {
      return new A();
    }
  }


  @Slf4j
  static class A {
    public void helloA () {
      log.info("hello A");
    }
  }

  @Slf4j
  static class B {
    public void helloB () {
      log.info("hello B");
    }
  }

}
