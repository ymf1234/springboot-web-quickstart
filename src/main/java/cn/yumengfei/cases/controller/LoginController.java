package cn.yumengfei.cases.controller;

import cn.yumengfei.cases.pojo.Emp;
import cn.yumengfei.cases.pojo.Result;
import cn.yumengfei.cases.service.TbEmpService;
import cn.yumengfei.cases.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private TbEmpService tbEmpService;

    @PostMapping
    public Result login(@RequestBody Emp emp) {
        Emp loginEmp = tbEmpService.login(emp);
        // 判断：登录用户是否存在
        if (loginEmp != null ) {
            // 自定义信息
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginEmp.getId());
            claims.put("username", loginEmp.getUsername());
            claims.put("name", loginEmp.getName());
            // 使用JWT工具类， 生成身份令牌
            String token = JwtUtils.generateJwt(claims);
            return Result.success(token);
        }
        return Result.error("用户名密码错误");
    }
}
