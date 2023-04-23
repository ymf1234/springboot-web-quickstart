package cn.yumengfei.cases.interceptors;

import cn.yumengfei.cases.pojo.Result;
import cn.yumengfei.cases.utils.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 自定义拦截器
@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    // 目标资源方法执行前执行。 返回 true：放行  返回 false: 不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle...");

        // 获取请求头中的令牌
        String token = request.getHeader("token");
        log.info("从请求头中获取的令牌：{}", token);

        // 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(token)) {
            log.info("Token不存在");

            // 创建响应结果对象
            Result responseResult = Result.error("NOT_LOGIN");

            // 把Result对象转换为JSON字符串
            String jsonString = JSONObject.toJSONString(responseResult);

            // 设置响应头（告知浏览器：响应的数据类型为json、响应的数据编码表为utf-8）
            response.setContentType("application/json;charset=utf-8");

            // 响应
            response.getWriter().write(jsonString);
            return false;
        }

        // 解析JWT令牌
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌解析失败！");

            // 创建响应结果对象
            Result responseResult = Result.error("NOT_LOGIN");
            // 把Result对象转换为JSON字符串
            String jsonString = JSONObject.toJSONString(responseResult);

            // 设置响应头（告知浏览器：响应的数据类型为json、响应的数据编码表为utf-8）
            response.setContentType("application/json;charset=utf-8");

            // 响应
            response.getWriter().write(jsonString);
            return false;
        }

        // true 表示放行
        return true;
    }

    // 目标资源方法执行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    // 视图渲染完毕后执行，最后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
