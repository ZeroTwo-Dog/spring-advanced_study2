package hello.proxy.app.v1;

/**
 * Created by park on 2022/04/10.
 */
public class OrderControllerV1Impl implements OrderControllerV1{

  private final OrderServiceV1 orderService;

  public OrderControllerV1Impl(OrderServiceV1 orderServiceV1) {
    this.orderService = orderServiceV1;
  }


  @Override
  public String request(String itemId) {
    orderService.orderItem(itemId);
    return "ok";
  }

  @Override
  public String noLong() {
    return "ok";
  }
}
