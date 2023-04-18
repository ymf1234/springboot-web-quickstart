package cn.yumengfei.mybatis.mapper;

import cn.yumengfei.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper // 表示是mybatis中的Mapper接口  程序运行时：框架会自动生成接口的实现类对象(代理对象)，并给交Spring的IOC容器管理
public interface UserMapper {

    // 查询所有用户数据
    @Select("select id, name, age, gender, phone from user") // 代表的就是select查询，用于书写select查询语句
    public List<User> list();


}
