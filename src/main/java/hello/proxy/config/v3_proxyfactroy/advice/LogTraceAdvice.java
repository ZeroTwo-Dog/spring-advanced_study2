package hello.proxy.config.v3_proxyfactroy.advice;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by park on 2022/05/04.
 */
public class LogTraceAdvice implements MethodInterceptor {

  private final LogTrace logTrace;

  public LogTraceAdvice(LogTrace logTrace) {
    this.logTrace = logTrace;
  }

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    TraceStatus status = null;
    try {
      Method method = invocation.getMethod();
      String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
      status = logTrace.begin(methodName);
      //target 호출
      Object result =  invocation.proceed();
      logTrace.end(status);

      return result;
    }catch (Exception e) {
      logTrace.end(status);
      throw e;
    }
  }
}
