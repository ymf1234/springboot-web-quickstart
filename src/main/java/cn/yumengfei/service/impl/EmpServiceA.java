package cn.yumengfei.service.impl;

import cn.yumengfei.dao.EmpDao;
import cn.yumengfei.dao.impl.EmpDaoA;
import cn.yumengfei.pojo.Emp;
import cn.yumengfei.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

//业务逻辑实现类（按照业务标准实现）
//@Component // 由IOC容器管理
@Service
public class EmpServiceA implements EmpService {
    // dao层对象
    @Autowired // IOC容器自动注入EmpDao类型的对象
    @Qualifier("empDaoA")
    private EmpDao empDao;

    @Override
    public List<Emp> listEmp() {
        //1. 调用dao, 获取数据
        List<Emp> empList = empDao.listEmp();

        //2. 对数据进行转换处理 - gender, job
        empList.forEach(emp -> {
            //处理 gender 1: 男, 2: 女
            String gender = emp.getGender();
            if ("1".equals(gender)) {
                emp.setGender("男");
            } else if ("2".equals(gender)) {
                emp.setGender("女");
            }

            //处理job - 1: 讲师, 2: 班主任 , 3: 就业指导
            String job = emp.getJob();
            if("1".equals(job)){
                emp.setJob("讲师");
            }else if("2".equals(job)){
                emp.setJob("班主任");
            }else if("3".equals(job)){
                emp.setJob("就业指导");
            }
        });

        return empList;
    }
}
