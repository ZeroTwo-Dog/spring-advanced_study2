package hello.proxy.app.v2;

import hello.proxy.app.v1.OrderRepositoryV1;

/**
 * Created by park on 2022/04/10.
 */
public class OrderRepositoryV2 {

  public void save(String itemId) {
    //저장로직
    if (itemId.equals("ex")) {
      throw  new IllegalStateException("예외발생!");
    } else {
      sleep(1000);
    }
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
