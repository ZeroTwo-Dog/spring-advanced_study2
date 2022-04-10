package hello.proxy.pureproxy.decorator.code;

import hello.proxy.pureproxy.code.Subject;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by park on 2022/04/10.
 */
@Slf4j
public class MessageDecorator implements Component {

  private Component component;
  public MessageDecorator(Component component) {
    this.component = component;
  }

  @Override
  public String operation() {
    log.info("MessageDecorator 호출");

    String result = component.operation();
    String decoResult = "*****" +result+ "++==";

    log.info("꾸미기 전={} , 꾸미기 후={}",result,decoResult);
    return decoResult;
  }
}
