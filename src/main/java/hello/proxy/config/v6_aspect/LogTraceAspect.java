package hello.proxy.config.v6_aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by park on 2022/05/05.
 */
@Slf4j
@Aspect
public class LogTraceAspect {

  private final LogTrace logTrace;

  public LogTraceAspect(LogTrace logTrace) {
    this.logTrace = logTrace;
  }

  @Around("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLong(..))")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

      TraceStatus status = null;

      try {
        String message = joinPoint.getSignature().toShortString();
        status = logTrace.begin(message);
        //로직 호출
        Object result = joinPoint.proceed();
        logTrace.end(status);
        return result;
      } catch (Exception e) {
        logTrace.exception(status, e);
        throw e;
      }
  }
}
