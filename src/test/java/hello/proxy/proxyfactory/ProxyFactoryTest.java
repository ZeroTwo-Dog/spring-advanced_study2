package hello.proxy.proxyfactory;

import static org.assertj.core.api.Assertions.*;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import javax.swing.PopupFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

/**
 * Created by park on 2022/05/04.
 */
@Slf4j
public class ProxyFactoryTest {
  
  
  @Test
  @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
  public void interfaceProxy() throws Exception {
    //given
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());
    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

    log.info("targetClass={}",target.getClass());
    log.info("proxyClass={}",proxy.getClass());

    proxy.save();

    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
  }


  @Test
  @DisplayName("구체클래스가 있으면 CGLIB 동적 프록시 사용")
  public void concreteProxy() throws Exception {
    //given
    ConcreteService target = new ConcreteService();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());
    ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

    log.info("targetClass={}",target.getClass());
    log.info("proxyClass={}",proxy.getClass());

    proxy.call();

    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
  }


  @Test
  @DisplayName("proxyTargetClass의 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시 사용")
  public void proxyTargetClass() throws Exception {
    //given
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());
    //인터페이스 여도 구체클래스처럼 CGLIB로 만듬
    proxyFactory.setProxyTargetClass(true);
    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

    log.info("targetClass={}",target.getClass());
    log.info("proxyClass={}",proxy.getClass());

    proxy.save();

    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
  }


}
