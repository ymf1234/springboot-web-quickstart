package cn.yumengfei.cases.service.impl;

import cn.yumengfei.cases.aop.OperateLog;
import cn.yumengfei.cases.mapper.DeptMapper;
import cn.yumengfei.cases.mapper.TbEmpMapper;
import cn.yumengfei.cases.pojo.Dept;
import cn.yumengfei.cases.pojo.DeptLog;
import cn.yumengfei.cases.service.DeptLogService;
import cn.yumengfei.cases.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门业务实现类
 */
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private TbEmpMapper tbEmpMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Override
    @OperateLog
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // 开启事务
    public void delete(Integer id) throws Exception {
        try {
            // 删除部门信息
            deptMapper.deleteById(id);

            // 模拟异常
            if (true) {
                throw new Exception("出现异常");
            }
            // 删除部门下的所有员工信息
            tbEmpMapper.deleteByDeptId(id);
        } finally {
            // 不论是否有异常, 最终都要执行的代码记录日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此时解散的是"+id+"号部门");
            //调用其他业务类中的方法
            deptLogService.insert(deptLog);
        }

    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }
}
