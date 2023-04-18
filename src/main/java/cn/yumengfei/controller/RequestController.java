package cn.yumengfei.controller;

import cn.yumengfei.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class RequestController {
    // 原始方式
    @RequestMapping("/simpleParam")
    public String simpleParam(HttpServletRequest request) {
        // http://localhost:8080/simpleParam?name=Tom&age=10
        // 请求参数： name=Tom&age=10   （有2个请求参数）
        // 第1个请求参数： name=Tom   参数名:name，参数值:Tom
        // 第2个请求参数： age=10     参数名:age , 参数值:10
        String name = request.getParameter("name"); //name就是请求参数名
        String ageStr = request.getParameter("age"); //age就是请求参数名
        int age = Integer.parseInt(ageStr);//需要手动进行类型转换
        System.out.println(name+"  :  "+age);
        return "OK";
    }

    // springBoot方式
    @RequestMapping("/simpleParamBySpringBoot")
    public String simpleParamBySpringBoot(String name, Integer age) {//形参名和请求参数名保持一致
        // http://localhost:8080/simpleParam?name=Tom&age=10
        // 第1个请求参数： name=Tom   参数名:name，参数值:Tom
        // 第2个请求参数： age=10     参数名:age , 参数值:10

        System.out.println(name+"  :  "+age);
        return "OK";
    }

    // springBoot 参数名不一致
    @RequestMapping("/simpleNoParamBySpringBoot")
    public String simpleNoParamBySpringBoot(@RequestParam(value = "name", required = false) String username, Integer age) {//形参名和请求参数名保持一致
        // http://localhost:8080/simpleParam?name=Tom&age=10
        // 第1个请求参数： username=Tom   参数名:name，参数值:Tom
        // 第2个请求参数： age=10     参数名:age , 参数值:10

        System.out.println(username+"  :  "+age);
        return "OK";
    }

    // 简单实体
    @RequestMapping("/simplePojo")
    public String simplePojo(User user) {
        System.out.println(user);
        return "OK";
    }

    // 复杂实体
    @RequestMapping("/complexPojo")
    public String complexPojo(User user) {
        System.out.println(user);
        return "OK";
    }

    // 数组
    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby) {
        System.out.println(Arrays.toString(hobby));
        return "OK";
    }

    // 集合
    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby) {
        System.out.println(hobby);
        return "OK";
    }

    // 日期参数
    @RequestMapping("/dateParam")
    public String dataParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {
        System.out.println(updateTime);
        return "OK";
    }

    // json参数
    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user){
        System.out.println(user);
        return "OK";
    }

    // 路径参数
    @RequestMapping("/path/{id}")
    public String pathParam(@PathVariable Integer id) {
        System.out.println(id);
        return "OK";
    }
}
