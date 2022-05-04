package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by park on 2022/05/04.
 */
@Slf4j
public class TimeAdvice implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    log.info("time 프록시 실행");
    long startTime = System.currentTimeMillis();

//    Object result = method.invoke(target, args);
    //위 주석의 타겟을 찾아서 실행시켜줌
    Object result = invocation.proceed();


    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;

    log.info("time 프록시 종료 time = {} ", resultTime);
    return result;
  }
}
