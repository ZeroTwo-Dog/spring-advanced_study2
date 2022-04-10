package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by park on 2022/04/10.
 */
@Slf4j
public class ConcreteLogic {


  public String operation () {
    log.info("ConcreteLogic 실행");
    return "data";
  }
}
