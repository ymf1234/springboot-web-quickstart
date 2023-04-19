package cn.yumengfei;

import cn.yumengfei.mybatis.mapper.EmpMapper;
import cn.yumengfei.mybatis.mapper.UserMapper;
import cn.yumengfei.mybatis.pojo.Emp;
import cn.yumengfei.mybatis.pojo.User;
import cn.yumengfei.pojo.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MybatisQuickstartApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testList() {
        List<User> list = userMapper.list();
        for (User usr: list) {
            System.out.println(usr);
        }
    }

    @Test
    public void testDel() {
        empMapper.delete(16);
    }

    @Test
    public void testInsert() {
        // 创建员工对象
        Emp emp = new Emp();
        emp.setUsername("tom1");
        emp.setName("汤姆");
        emp.setImage("1.jpg");
        emp.setGender((short)1);
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.of(2000,1,1));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);
        empMapper.insert(emp);
        System.out.println("=========================");
        System.out.println(emp.getId());
    }

    @Test
    public void testUpdate() {
        //要修改的员工信息
        Emp emp = new Emp();
        emp.setId(1);
//        emp.setUsername("songdaxia");
        emp.setPassword(null);
        emp.setName("张无忌1");
//        emp.setImage("2.jpg");
//        emp.setGender((short)1);
//        emp.setJob((short)2);
//        emp.setEntrydate(LocalDate.of(2012,1,1));
//        emp.setCreateTime(null);
//        emp.setUpdateTime(LocalDateTime.now());
//        emp.setDeptId(2);
        //调用方法，修改员工数据
        empMapper.update(emp);
    }

    @Test
    public void testGetById() {
        Emp emp = empMapper.getById(1);
        System.out.println(emp);
    }

    @Test
    public void testEmpList() {
        empMapper.list("张", null,null,null);
    }

    @Test
    public void deleteByIds() {
        List<Integer> list = new ArrayList<>();
        list.add(22);
        list.add(21);
        empMapper.deleteByIds(list);
    }
}
