package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * Created by park on 2022/05/04.
 */
@Slf4j
public class AdvisorTest {
  
  @Test
  public void advisorTest1() throws Exception {
    //given
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);

    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE,
        new TimeAdvice());
    proxyFactory.addAdvisor(advisor);

    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

    proxy.save();
    proxy.find();

    //when
    
    //then
  }
  
  
  @Test
  @DisplayName("직접만드는 포인터컷")
  public void advisorTest2() throws Exception {
    //given
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);

    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointCut(),
        new TimeAdvice());
    proxyFactory.addAdvisor(advisor);

    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

    proxy.save();
    proxy.find();

    //when

    //then
  }

  static class MyPointCut  implements Pointcut{

    @Override
    public ClassFilter getClassFilter() {
      return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {

      return new MyMethodMatcher();
    }
  }

  static class MyMethodMatcher implements MethodMatcher{

    private String matchName = "save";

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
      boolean save = method.getName().equals(matchName);
      log.info("포인트컷 호출  method = {} targetClass ={}",method.getName(), targetClass);
      log.info("포인트컷 결과  result = {}",save);

      return save;
    }

    @Override
    public boolean isRuntime() {
      return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
      throw new UnsupportedOperationException();    }
  }


  @Test
  @DisplayName("스프링이 제공하는 포인터컷")
  public void advisorTest3() throws Exception {
    //given
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedName("save");


    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut,
        new TimeAdvice());
    proxyFactory.addAdvisor(advisor);

    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

    proxy.save();
    proxy.find();

    //when

    //then
  }

}
