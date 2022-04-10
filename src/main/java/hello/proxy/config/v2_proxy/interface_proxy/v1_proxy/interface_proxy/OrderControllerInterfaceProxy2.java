package hello.proxy.config.v2_proxy.interface_proxy.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

/**
 * Created by park on 2022/04/10.
 */
public class OrderControllerInterfaceProxy2 extends OrderControllerV2 {

  private final OrderControllerV2 target;
  private final LogTrace logTrace;

  public OrderControllerInterfaceProxy2(
      OrderControllerV2 target, LogTrace logTrace) {
    super(null);
    this.target = target;
    this.logTrace = logTrace;
  }




  @Override
  public String request(String itemId) {
    TraceStatus status = null;
    try {
      status = logTrace.begin("OrderController.Request");
      //target 호출
      String result = target.request(itemId);
      logTrace.end(status);

      return result;
    }catch (Exception e) {
      logTrace.end(status);
      throw e;
    }
  }

  @Override
  public String noLong() {
    return target.noLong();
  }


}
