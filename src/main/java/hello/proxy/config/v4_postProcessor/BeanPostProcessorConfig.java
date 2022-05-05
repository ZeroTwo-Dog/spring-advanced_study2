package hello.proxy.config.v4_postProcessor;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v3_proxyfactroy.advice.LogTraceAdvice;
import hello.proxy.config.v4_postProcessor.postProcessor.PackageLogTracePostProcessor;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by park on 2022/05/05.
 */
@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {


  @Bean
  public PackageLogTracePostProcessor packageLogTracePostProcessor (LogTrace logTrace) {
    return new PackageLogTracePostProcessor("hello.proxy.app",getAdvisor(logTrace));
  }

  private Advisor getAdvisor(LogTrace logTrace) {
    //pointcut
    NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedNames("request*", "order*", "save*");
    //advice
    LogTraceAdvice advice = new LogTraceAdvice(logTrace);
    //advisor = pointcut + advice
    return new DefaultPointcutAdvisor(pointcut, advice);

  }

  @Bean
  public LogTrace  logTrace5() {
    return new ThreadLocalLogTrace();
  }
}
