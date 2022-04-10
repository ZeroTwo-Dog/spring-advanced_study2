package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by park on 2022/04/10.
 */
@Slf4j
public class TimeProxy extends ConcreteLogic {


  private ConcreteLogic concreteLogic;

  public TimeProxy(ConcreteLogic concreteLogic) {
    this.concreteLogic = concreteLogic;
  }

  @Override
  public String operation() {
    log.info("TimeDecorator 호출");
    long startTime = System.currentTimeMillis();


    String result = concreteLogic.operation();

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("TimeDecorator 호출 resultTime = {}" ,resultTime);
    return result;
  }
}
