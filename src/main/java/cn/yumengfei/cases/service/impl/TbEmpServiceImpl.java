package cn.yumengfei.cases.service.impl;


import cn.yumengfei.cases.mapper.TbEmpMapper;
import cn.yumengfei.cases.pojo.Emp;
import cn.yumengfei.cases.pojo.PageBean;
import cn.yumengfei.cases.service.TbEmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工业务实现类
 */
@Slf4j
@Service
public class TbEmpServiceImpl implements TbEmpService {
    @Autowired
    private TbEmpMapper tbEmpMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
//        // 获取总条数
//        Long count = tbEmpMapper.count();
        // 设置分页参数
        PageHelper.startPage(page, pageSize);

//         获取当前页
//        Integer start = (page - 1) * pageSize;

        // 执行分页查询
        List<Emp> empList = tbEmpMapper.list(name, gender, begin, end);

        // 获取分页结果
        Page<Emp> p = (Page<Emp>) empList;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        tbEmpMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        tbEmpMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return tbEmpMapper.findById(id);
    }

    @Override
    public void update(Emp emp) {
        //更新修改时间为当前时间
        emp.setUpdateTime(LocalDateTime.now());
        tbEmpMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        Emp loginEmp = tbEmpMapper.getByUsernameAndPassword(emp);

        return loginEmp;
    }
}
