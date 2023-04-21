package cn.yumengfei.cases.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


//定义一个类，实现一个标准的Filter过滤器的接口
//@WebFilter(urlPatterns = "/*") //配置过滤器要拦截的请求路径（ /* 表示拦截浏览器的所有请求 ）
//@WebFilter(urlPatterns = "/login") // 拦截具体login路径
public class DemoFilter implements Filter {
    //初始化方法, 只调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 初始化方法执行了");
    }

    //拦截到请求之后调用, 调用多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo 拦截到了请求...放行前逻辑");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("DemoFilter 放行后逻辑.....");
    }


    //销毁方法, 只调用一次
    @Override
    public void destroy() {
        System.out.println("destroy 销毁方法执行了");
    }
}
