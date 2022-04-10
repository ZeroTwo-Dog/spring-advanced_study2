package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by park on 2022/04/10.
 */
@Slf4j
public class ConcreteClient {

  private ConcreteLogic concreteLogic;

  public ConcreteClient(ConcreteLogic concreteLogic) {
    this.concreteLogic = concreteLogic;
  }

  public void execute () {
    concreteLogic.operation();
  }
}
