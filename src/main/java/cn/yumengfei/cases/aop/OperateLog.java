package cn.yumengfei.cases.aop;

import java.lang.annotation.*;

/**
 * 自定义Log注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {
}
