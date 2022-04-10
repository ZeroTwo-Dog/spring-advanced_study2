package hello.proxy.pureproxy.decorator.code;

import hello.proxy.pureproxy.code.Subject;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by park on 2022/04/10.
 */
@Slf4j
public class DecoratorPatternClient {

  private Component component;

  public DecoratorPatternClient(Component component) {
    this.component = component;
  }

  public void execute() {
    String result = component.operation();
    log.info("result={}", result);
  }
}
