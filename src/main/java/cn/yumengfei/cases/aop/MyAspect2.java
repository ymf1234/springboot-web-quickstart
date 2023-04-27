package cn.yumengfei.cases.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class MyAspect2 {
    // 引用其他类切面类中的切入点表达式
    @Before("cn.yumengfei.cases.aop.TimeAspect.pt()")
    public void before() {
        log.info("MyAspect2 -> before ...");
    }
}
