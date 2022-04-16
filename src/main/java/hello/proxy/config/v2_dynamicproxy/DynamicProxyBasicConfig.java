package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import java.lang.reflect.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by park on 2022/04/16.
 */
@Configuration
public class DynamicProxyBasicConfig {

  @Bean
  public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
    OrderControllerV1 orderController = new
        OrderControllerV1Impl(orderServiceV1(logTrace));
    OrderControllerV1 proxy = (OrderControllerV1)
        Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
            new Class[]{OrderControllerV1.class},
            new LogTraceBasicHandler(orderController, logTrace)
        );
    return proxy;
  }


  @Bean
  public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
    OrderServiceV1 orderService = new
        OrderServiceV1Impl(orderRepositoryV1(logTrace));
    OrderServiceV1 proxy = (OrderServiceV1)
        Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
            new Class[]{OrderServiceV1.class},
            new LogTraceBasicHandler(orderService, logTrace)
        );
    return proxy;
  }


  @Bean
  public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
    OrderRepositoryV1 repositoryV1 = new OrderRepositoryV1Impl();

    return (OrderRepositoryV1) Proxy.newProxyInstance(
        OrderRepositoryV1.class.getClassLoader(),
        new Class[]{OrderRepositoryV1.class}, new LogTraceBasicHandler(repositoryV1, logTrace));
  }

  @Bean
  public LogTrace  logTrace3() {
    return new ThreadLocalLogTrace();
  }
}
