package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

  // @Bean memberService -> new MemoryMemberRepository()
  // @Bean orderService -> new MemoryMemberRepository()

  @Test
  void statefulServiceSingleton() {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
    StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

    // ThreadA: A사용자 10000원 주문
    statefulService1.order("userA", 10000);
    // ThreadB: B사용자 20000원 주문
    statefulService2.order("userB", 20000);

    // ThreadA: A사용자 주문 금액 조회
    int price = statefulService1.getPrice();
    System.out.println("price = " + price);

    assertEquals(20000, statefulService1.getPrice());
  }

  static class TestConfig {

    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }
  }
}