package hello.proxy.common.service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by park on 2022/05/04.
 */
@Slf4j
public class ConcreteService {
  public void call() { log.info("ConcreteService 호출");
  } }
