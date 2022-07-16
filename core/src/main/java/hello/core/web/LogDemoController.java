package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

  private final LogDemoService logDemoService;
  private final MyLogger myLogger;

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) throws InterruptedException {

    String requestURL = request.getRequestURL().toString();

    System.out.println("myLogger1 = " + myLogger.getClass());
    myLogger.setRequestURL(requestURL);

    myLogger.log("controller test");
    Thread.sleep(2000);
    System.out.println("myLogger2 = " + myLogger.getClass());
    logDemoService.logic("testId");
    return "OK";
  }
}
