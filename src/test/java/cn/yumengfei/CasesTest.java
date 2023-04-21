package cn.yumengfei;

import cn.yumengfei.cases.mapper.TbEmpMapper;
import cn.yumengfei.cases.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CasesTest {
    @Autowired
    private TbEmpMapper tbEmpMapper;
    @Test
    public void list() {
        List<Emp> empList = tbEmpMapper.list("张", null, null, null);
        System.out.println(empList);
    }
}
