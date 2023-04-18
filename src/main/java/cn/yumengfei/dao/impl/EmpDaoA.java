package cn.yumengfei.dao.impl;

import cn.yumengfei.dao.EmpDao;
import cn.yumengfei.pojo.Emp;
import cn.yumengfei.utils.XmlParserUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

//@Component // 由IOC容器管理
@Repository
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {
        //1. 加载并解析emp.xml
//        String file = this.getClass().getClassLoader().getResource("static/emp.xml").getFile();
        String file = Objects.requireNonNull(this.getClass().getClassLoader().getResource("static/emp.xml")).getFile();
        return XmlParserUtils.parse(file, Emp.class);
    }
}
