package hello.proxy.jdkdynamics.code;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by park on 2022/04/16.
 */
@Slf4j
public class AImpl implements AInterface{

  @Override
  public String call() {
    log.info("로그 A 호출");
    return "A";
  }
}
