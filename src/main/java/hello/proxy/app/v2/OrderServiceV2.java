package hello.proxy.app.v2;

/**
 * Created by park on 2022/04/10.
 */

public class OrderServiceV2 {

  private final OrderRepositoryV2 orderRepository;

  public OrderServiceV2(OrderRepositoryV2 orderRepository) {
    this.orderRepository = orderRepository;
  }



  public void orderItem(String itemId) {
    orderRepository.save(itemId);
  }
}
