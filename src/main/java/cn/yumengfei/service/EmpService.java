package cn.yumengfei.service;

import cn.yumengfei.pojo.Emp;

import java.util.List;

//业务逻辑接口（制定业务标准）
public interface EmpService {
    //获取员工列表
    public List<Emp> listEmp();
}
