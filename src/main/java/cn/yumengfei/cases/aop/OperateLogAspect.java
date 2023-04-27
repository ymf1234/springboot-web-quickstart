package cn.yumengfei.cases.aop;

import cn.yumengfei.cases.mapper.OperateLogMapper;
import cn.yumengfei.cases.pojo.OperateLog;
import cn.yumengfei.cases.utils.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect // 切面类
public class OperateLogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Pointcut("@annotation(cn.yumengfei.cases.aop.OperateLog)")
    public void pt() {

    }

    @Around("pt()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 操作人ID - 当前登录用户
        // 获取请求头中的jwt令牌,解析令牌
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer operateUserId = (Integer) claims.get("id");

        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 操作类名
        String className = joinPoint.getTarget().getClass().getName();

        // 操作方法
        String methodName = joinPoint.getSignature().getName();

        // 操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        // 方法返回值
        String returnValue = JSONObject.toJSONString(result);

        // 操作耗用
        Long costTime = end - begin;

        //记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUserId,operateTime,
                className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志: {}", operateLog);

        return  result;
    }
}
