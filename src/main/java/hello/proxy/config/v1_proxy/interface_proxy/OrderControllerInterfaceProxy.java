package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Created by park on 2022/04/10.
 */
@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

  private final OrderControllerV1 target;
  private final LogTrace logTrace;

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
