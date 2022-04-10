package hello.proxy.pureproxy.code;

/**
 * Created by park on 2022/04/10.
 */
public class ProxyPatternClient {

  private Subject subject;


  public ProxyPatternClient(Subject subject) {
    this.subject = subject;
  }

  public void execute() {
    subject.operation();
  }
}
