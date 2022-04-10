package hello.proxy.config;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by park on 2022/04/10.
 */
@Configuration
public class AppV2Config {


  @Bean
  public OrderControllerV2 orderController() {
    return new OrderControllerV2(orderService());
  }

  @Bean
  public OrderServiceV2 orderService() {
    return new OrderServiceV2(orderRepository());
  }

  @Bean
  public OrderRepositoryV2 orderRepository (){
    return new OrderRepositoryV2();
  }
}
