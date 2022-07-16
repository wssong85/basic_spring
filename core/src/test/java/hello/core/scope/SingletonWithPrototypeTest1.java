package hello.core.scope;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

  @Test
  void prototypeFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    prototypeBean1.addCount();
    Assertions.assertEquals(1, prototypeBean1.getCount());

  }

  @Test
  void singletonClientUsePrototype() {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
    ClientBean clientBean1 = ac.getBean(ClientBean.class);

    int count1 = clientBean1.logic();
    Assertions.assertEquals(1, count1);

    ClientBean clientBean2 = ac.getBean(ClientBean.class);

    int count2 = clientBean2.logic();
    Assertions.assertEquals(1, count2);
  }

  @Scope("singleton")
  static class ClientBean {

    @Autowired
    private Provider<PrototypeBean> prototypeBeanProvider;

    public int logic() {
      PrototypeBean prototypeBean = prototypeBeanProvider.get();
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Scope("prototype")
  static class PrototypeBean {

    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init -> " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}
