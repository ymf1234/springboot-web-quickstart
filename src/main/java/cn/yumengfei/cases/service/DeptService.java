package cn.yumengfei.cases.service;

import cn.yumengfei.cases.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门业务规则
 */
public interface DeptService {
    /**
     * 查询所有的部门数据
     * @return 存储Dept对象的集合
     */
    List<Dept> list();

    /**
     * 删除部门
     * @param id
     */
    void delete(Integer id);

    void add(Dept dept);
}
