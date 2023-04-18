package cn.yumengfei.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // //getter方法、setter方法、toString方法、hashCode方法、equals方法
@NoArgsConstructor // 生成没有参数构造方法
@AllArgsConstructor // 生成全参数构造方法
public class User {
    private Integer id;   //id（主键）
    private String name;  //姓名
    private Short age;    //年龄
    private Short gender; //性别
    private String phone; //手机号
}
