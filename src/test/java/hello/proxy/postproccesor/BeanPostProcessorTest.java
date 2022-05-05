package hello.proxy.postproccesor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by park on 2022/05/05.
 */

public class BeanPostProcessorTest {

  @Test
  public void basicConfig() {
    //given
    ApplicationContext context = new AnnotationConfigApplicationContext(basicConfig.class);
    //beanA 이름으로 B객체로 등록된다. 바꿔치기해버림
    B b = context.getBean("beanA",B.class);
    b.helloB();

    //when

    //then
    Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(A.class));
  }

  @Slf4j
  @Configuration
  static class basicConfig {

    @Bean(name = "beanA")
    public A a() {
      return new A();
    }

    @Bean
    public AtoBPostProcessor atoBPostProcessor () {
      return new AtoBPostProcessor();
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


  @Slf4j
  static class AtoBPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {

      log.info("beanName = {} bean = {}", beanName, bean);

      if (bean instanceof A) {
        return new B();
      }
      return bean;

    }
  }

}
