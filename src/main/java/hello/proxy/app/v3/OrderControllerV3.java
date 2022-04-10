package hello.proxy.app.v3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController// 스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러로 인식
@ResponseBody
public class OrderControllerV3 {

  private final OrderServiceV3 orderService;

  public OrderControllerV3(OrderServiceV3 orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/v3/request")
  public String request(@RequestParam("itemId") String itemId) {
    orderService.orderItem(itemId);
    return "ok";
  }

  @GetMapping("/v3/no-log")
  public String noLong() {
    return "ok";
  }

}
