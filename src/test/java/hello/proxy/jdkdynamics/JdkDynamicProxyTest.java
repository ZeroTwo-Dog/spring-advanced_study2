package hello.proxy.jdkdynamics;

import hello.proxy.jdkdynamics.code.AImpl;
import hello.proxy.jdkdynamics.code.AInterface;
import hello.proxy.jdkdynamics.code.TimeInvocationHandler;
import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Created by park on 2022/04/16.
 */
@Slf4j
public class JdkDynamicProxyTest {


  @Test
  void dynamicA () {
    AInterface target = new AImpl();

    TimeInvocationHandler handler = new TimeInvocationHandler(target);

    AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),
        new Class[]{AInterface.class}, handler);

    proxy.call();
    log.info("targetClass ={}",target.getClass());
    log.info("proxyxClass ={}",proxy.getClass());
  }

}
