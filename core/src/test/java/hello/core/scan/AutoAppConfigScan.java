package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class AutoAppConfigScan {

  @Test
  void basicScan() {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    MemberService memberService = ac.getBean(MemberService.class);
    assertInstanceOf(MemberService.class, memberService);

    OrderService orderService = ac.getBean(OrderService.class);
    System.out.println("orderService = " + orderService);
  }

  @Test
  void fieldInjectionTest() {
//    OrderServiceImpl orderService = new OrderServiceImpl();
//    orderService.createOrder()
  }
}
