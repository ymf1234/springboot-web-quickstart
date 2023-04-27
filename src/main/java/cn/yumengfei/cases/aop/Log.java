package cn.yumengfei.cases.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class Log {

    @Pointcut("@annotation(cn.yumengfei.cases.aop.MyLog)")
    private void pt() {}

    //前置通知
    @Before("pt()")
    public void before(){
        log.info("MyAspect6 -> before ...");
    }

    //后置通知
    @After("pt()")
    public void after(){
        log.info("MyAspect6 -> after ...");
    }

    // 环绕通知
    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标类名
        String name = joinPoint.getTarget().getClass().getName();
        log.info("目标类名: {}", name);

        // 目标方法名
        String methodName = joinPoint.getSignature().getName();
        log.info("目标方法名: {}", methodName);

        // 获取方法执行时需要的参数
        Object[] args = joinPoint.getArgs();
        log.info("目标方法参数: {}", Arrays.toString(args));

        // 执行原始方法
        Object returnValue = joinPoint.proceed();

        return returnValue;
    }
}
