package hello.proxy.pureproxy.concreteproxy;

import hello.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import org.junit.jupiter.api.Test;

/**
 * Created by park on 2022/04/10.
 */
public class ConcreteProxyTest {

  @Test
  void noProxy() {
    ConcreteLogic concreteLogic = new ConcreteLogic();
    ConcreteClient client = new ConcreteClient(concreteLogic);

    client.execute();
  }

}
