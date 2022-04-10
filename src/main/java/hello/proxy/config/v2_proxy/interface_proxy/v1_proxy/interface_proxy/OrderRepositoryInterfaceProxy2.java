package hello.proxy.config.v2_proxy.interface_proxy.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

/**
 * Created by park on 2022/04/10.
 */
public class OrderRepositoryInterfaceProxy2 extends OrderRepositoryV2 {

  private final OrderRepositoryV2 target;
  private final LogTrace logTrace;

  public OrderRepositoryInterfaceProxy2(OrderRepositoryV2 target,
      LogTrace logTrace) {
    this.target = target;
    this.logTrace = logTrace;
  }

  @Override
  public void save(String itemId) {
    TraceStatus status = null;
    try {
      status = logTrace.begin("OrderRepository request");
      //target 호출
      target.save(itemId);
      logTrace.end(status);
    }catch (Exception e) {
      logTrace.end(status);
      throw e;
    }
  }

}
