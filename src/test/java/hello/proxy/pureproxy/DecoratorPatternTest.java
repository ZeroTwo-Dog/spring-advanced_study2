package hello.proxy.pureproxy;

import hello.proxy.pureproxy.code.CacheProxy;
import hello.proxy.pureproxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.code.RealSubject;
import hello.proxy.pureproxy.decorator.code.Component;
import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import org.junit.jupiter.api.Test;

/**
 * Created by park on 2022/04/10.
 */
public class DecoratorPatternTest {


  @Test
  void noDecorator() {
    Component component = new RealComponent();
    DecoratorPatternClient client = new DecoratorPatternClient(component);

    client.execute();

  }

  @Test
  void decoratorTest1() {
    Component component = new RealComponent();
    MessageDecorator decorator = new MessageDecorator(component);
    DecoratorPatternClient client = new DecoratorPatternClient(decorator);

    client.execute();

  }

  @Test
  void decoratorTest2() {
    Component component = new RealComponent();
    Component messageDecorator = new MessageDecorator(component);
    Component timeDecorator = new TimeDecorator(messageDecorator);
    DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

    client.execute();

  }

}
