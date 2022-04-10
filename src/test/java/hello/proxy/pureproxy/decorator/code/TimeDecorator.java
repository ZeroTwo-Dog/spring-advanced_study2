package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by park on 2022/04/10.
 */
@Slf4j
public class TimeDecorator implements Component {

  private Component component;
  public TimeDecorator(Component component) {
    this.component = component;
  }

  @Override
  public String operation() {
    log.info("TimeDecorator 호출");
    long startTime = System.currentTimeMillis();


    String result = component.operation();

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("TimeDecorator 호출 resultTime = {}" ,resultTime);
    return result;
  }
}
