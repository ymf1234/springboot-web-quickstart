package cn.yumengfei.cases.service;

import cn.yumengfei.cases.pojo.Emp;
import cn.yumengfei.cases.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工业务规则
 */
public interface TbEmpService {
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void save(Emp emp);

    public Emp getById(Integer id);

    public void update(Emp emp);

    public Emp login(Emp emp);
}
