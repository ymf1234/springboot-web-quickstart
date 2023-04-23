package cn.yumengfei.cases.service.impl;

import cn.yumengfei.cases.mapper.DeptLogMapper;
import cn.yumengfei.cases.pojo.DeptLog;
import cn.yumengfei.cases.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;

    @Override
    @Transactional //事务传播行为：有事务就加入、没有事务就新建事务
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
