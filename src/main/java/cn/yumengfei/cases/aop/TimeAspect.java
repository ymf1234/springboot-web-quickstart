package cn.yumengfei.cases.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
//@Aspect // 当前类为切面类
@Slf4j
public class TimeAspect {

    @Pointcut("execution(* cn.yumengfei.cases.service.*.*(..))")
    public void pt(){}

    // 前置通知
    @Before("pt()")
    public void before(JoinPoint joinPoint) {
        log.info("before ...");
    }


    // 环绕通知
    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录方法执行开始时间
        long begin = System.currentTimeMillis();

        // 执行原始方法
        Object result = joinPoint.proceed();

        // 记录方式执行结束时间
        long end = System.currentTimeMillis();

        // 记录方法执行耗时
        log.info(joinPoint.getSignature() + "执行耗时: {} 毫秒", end - begin);
        return result;
    }

    // 后置通知
    @After("pt()")
    public void after(JoinPoint joinPoint) {
        log.info("after ...");
    }

    // 返回后通知(程序在正常执行的情况下，会执行的后置通知)
    @AfterReturning("pt()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("afterReturning ...");
    }

    // 异常通知(程序在出现异常的情况下，执行的后置通知)
    @AfterThrowing("pt()")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("afterThrowing ...");
    }
}
