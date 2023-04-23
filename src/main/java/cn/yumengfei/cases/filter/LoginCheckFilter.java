package cn.yumengfei.cases.filter;

import cn.yumengfei.cases.pojo.Result;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 前置：前置转换为http协议的请求对象、响应对象 （转换原因：要使用子类中特有方法）
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求路径：{}", url); // 请求路径

        // 判断请求url是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            filterChain.doFilter(request, response);
            return; // 结束当前方法执行
        }

        // 获取请求头中的令牌（token）
        String token = request.getHeader("token");
        log.info("从请求头中获取令牌：{}", token);

        // 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(token)) {
            log.info("token不存在");

            Result responseResult = Result.error("NOL_LONG");

            // 把Result对象转换为JOSN格式字符串（使用fastjson）
            String jsonString = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json;charset=utf-8");
            // 响应
            response.getWriter().write(jsonString);

            return;
        }

        // 放行
        filterChain.doFilter(request, response);
    }
}
