package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

  @Test
  void findAllBean() {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

    DiscountService discountService = ac.getBean(DiscountService.class);
    Member userA = new Member(1L, "userA", Grade.VIP);
    int discountPrice = discountService.discount(userA, 10000, "fixDiscountPolicy");

    Assertions.assertInstanceOf(DiscountService.class, discountService);
    Assertions.assertEquals(1000, discountPrice);

    int rateDiscountPrice = discountService.discount(userA, 20000, "rateDiscountPolicy");
    Assertions.assertEquals(2000, rateDiscountPrice);
  }

  static class DiscountService {

    private final Map<String, DiscountPolicy> policyMap;
    private final List<DiscountPolicy> polices;

    @Autowired
    public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> polices) {
      this.policyMap = policyMap;
      this.polices = polices;
      System.out.println("policyMap = " + policyMap);
      System.out.println("polices = " + polices);
    }

    public int discount(Member member, int price, String discountCode) {
      DiscountPolicy discountPolicy = policyMap.get(discountCode);
      return discountPolicy.discount(member, price);
    }
  }
}
