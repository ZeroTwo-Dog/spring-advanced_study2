package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by park on 2022/04/10.
 */
@Slf4j
public abstract class Decorator implements Component {

  public Component component;
  public Decorator(Component component) {
    this.component = component;
  }

  @Override
  public abstract String operation();
}
