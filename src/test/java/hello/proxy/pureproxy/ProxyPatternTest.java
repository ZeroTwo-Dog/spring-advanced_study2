package hello.proxy.pureproxy;

import hello.proxy.pureproxy.code.CacheProxy;
import hello.proxy.pureproxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.code.RealSubject;
import org.junit.jupiter.api.Test;

/**
 * Created by park on 2022/04/10.
 */
public class ProxyPatternTest {


  @Test
  void noProxyTest() {
    RealSubject subject = new RealSubject();
    ProxyPatternClient client = new ProxyPatternClient(subject);

    client.execute();
    client.execute();
    client.execute();

  }

  @Test
  void proxyTest() {
    RealSubject subject = new RealSubject();
    CacheProxy cacheProxy = new CacheProxy(subject);
    ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

    client.execute();
    client.execute();
    client.execute();

  }

}
