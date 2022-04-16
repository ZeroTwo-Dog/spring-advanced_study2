package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.springframework.util.PatternMatchUtils;

/**
 * Created by park on 2022/04/16.
 */
public class LogTraceFilterHandler implements InvocationHandler {

  private final Object target;
  private final LogTrace logTrace;
  private final String[] patterns;

  public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] patterns) {
    this.target = target;
    this.logTrace = logTrace;
    this.patterns = patterns;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    String name = method.getName();

    //메서드명이 매칭되지 않으면 단순실행
    if (!PatternMatchUtils.simpleMatch(patterns,name)) {
      return method.invoke(target, args);
    }

    TraceStatus status = null;
    try {
      String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
      status = logTrace.begin(methodName);
      //target 호출
      Object result =  method.invoke(target, args);
      logTrace.end(status);

      return result;
    }catch (Exception e) {
      logTrace.end(status);
      throw e;
    }

  }
}
