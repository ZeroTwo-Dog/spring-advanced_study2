package hello.proxy.app.v3;

import org.springframework.stereotype.Repository;

/**
 * Created by park on 2022/04/10.
 */
@Repository
public class OrderRepositoryV3 {

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
