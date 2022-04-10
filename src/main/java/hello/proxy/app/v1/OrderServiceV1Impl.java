package hello.proxy.app.v1;

/**
 * Created by park on 2022/04/10.
 */
public class OrderServiceV1Impl implements OrderServiceV1 {

  private final OrderRepositoryV1 orderRepository;

  public OrderServiceV1Impl(OrderRepositoryV1 orderRepositoryV1) {
    this.orderRepository = orderRepositoryV1;
  }


  @Override
  public void orderItem(String itemId) {
    orderRepository.save(itemId);
  }
}
