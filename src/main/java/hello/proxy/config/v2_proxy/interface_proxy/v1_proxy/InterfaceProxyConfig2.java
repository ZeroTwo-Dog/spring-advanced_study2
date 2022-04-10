package hello.proxy.config.v2_proxy.interface_proxy.v1_proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v2_proxy.interface_proxy.v1_proxy.interface_proxy.OrderControllerInterfaceProxy2;
import hello.proxy.config.v2_proxy.interface_proxy.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy2;
import hello.proxy.config.v2_proxy.interface_proxy.v1_proxy.interface_proxy.OrderServiceInterfaceProxy2;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by park on 2022/04/10.
 */
@Configuration
public class InterfaceProxyConfig2 {

  @Bean
  public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
    OrderControllerV2 controllerV = new OrderControllerV2(orderServiceV2(logTrace));

    return new OrderControllerInterfaceProxy2(controllerV, logTrace);

  }

  @Bean
  public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
    OrderServiceV2 orderService = new OrderServiceV2(orderRepositoryV2(logTrace));
    return new OrderServiceInterfaceProxy2(orderService, logTrace);
  }

  @Bean
  public OrderRepositoryV2 orderRepositoryV2 (LogTrace logTrace){
    OrderRepositoryV2 orderRepository = new OrderRepositoryV2();
    return new OrderRepositoryInterfaceProxy2(orderRepository, logTrace);
  }


  @Bean
  public LogTrace  logTrace2() {
    return new ThreadLocalLogTrace();
  }
}
