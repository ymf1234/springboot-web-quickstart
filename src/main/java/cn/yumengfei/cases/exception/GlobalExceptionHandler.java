package cn.yumengfei.cases.exception;

import cn.yumengfei.cases.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 定义一个全局异常处理器
public class GlobalExceptionHandler {

    // 处理异常
    @ExceptionHandler(Exception.class) // 指定能够处理异常类型
    public Result ex(Exception e) {
        e.printStackTrace(); // 打印堆栈中的异常信息

        // 捕获到异常之后，响应一个标准的Result
        return Result.error("对不起,操作失败,请联系管理员" + e.getMessage());
    }

}
