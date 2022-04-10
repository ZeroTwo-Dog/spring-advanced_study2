package hello.proxy.config.v2_proxy.interface_proxy.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

/**
 * Created by park on 2022/04/10.
 */
public class OrderServiceInterfaceProxy2 extends OrderServiceV2 {

  private final OrderServiceV2 target;
  private final LogTrace logTrace;


  public OrderServiceInterfaceProxy2(
      OrderServiceV2 target, LogTrace logTrace) {
    super(null);
    this.target = target;
    this.logTrace = logTrace;
  }


  @Override
  public void orderItem(String itemId) {
    TraceStatus status = null;
    try {
      status = logTrace.begin("OrderService.orderItem()");
      //target 호출
      target.orderItem(itemId);
      logTrace.end(status);
    }catch (Exception e) {
      logTrace.end(status);
      throw e;
    }
  }


}
