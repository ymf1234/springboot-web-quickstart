package cn.yumengfei.cases.controller;

import cn.yumengfei.cases.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 会话
 */
@Slf4j
@RestController
public class SessionController {

    // 设置cookie
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response) {
        // 设置cookie
        response.addCookie(new Cookie("login_username", "cookie"));

        // 响应Cookie
        return Result.success();
    }

    // 获取cookie
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login_username")) {
                System.out.println("login_username: " + cookie.getValue());
            }
        }

        return Result.success();
    }

    // 设置Session
    @GetMapping("/s1")
    public Result session1(HttpSession session) {
        log.info("HttpSession-s1: {}", session.hashCode());

        // 往session中存储数据
        session.setAttribute("loginUser", "tom");
        return Result.success();
    }

    // 获取Session
    @GetMapping("/s2")
    public Result session2(HttpServletRequest request) {
        HttpSession session = request.getSession();

        log.info("HttpSession-s1: {}", session.hashCode());

        Object loginUser = session.getAttribute("loginUser");//从session中获取数据

        log.info("loginUser: {}", loginUser);
        return Result.success(loginUser);
    }
}
