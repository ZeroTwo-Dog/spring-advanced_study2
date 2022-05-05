package hello.proxy.config.v4_postProcessor.postProcessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by park on 2022/05/05.
 */
@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {

  private final String basePackage;
  private final Advisor advisor;


  public PackageLogTracePostProcessor(String basePackage, Advisor advisor) {
    this.basePackage = basePackage;
    this.advisor = advisor;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    log.info("beanName = {} bean = {}", beanName, bean);

    //프록시 적용대상 여부 체크
    String packageName = bean.getClass().getPackage().getName();

    //프록시 적용 대상이 아니면 원본 리턴
    if (!packageName.startsWith(basePackage)) {
      return bean;
    }

    //프록시 적용이면 프록시를 리턴
    ProxyFactory proxyFactory = new ProxyFactory(bean);
    proxyFactory.addAdvisor(advisor);

    Object proxy = proxyFactory.getProxy();
    log.info("create proxy: target = {} proxy ={}", bean.getClass(), proxy.getClass());
    return proxy;

  }
}
