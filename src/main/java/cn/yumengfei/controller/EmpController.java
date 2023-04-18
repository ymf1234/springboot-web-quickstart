package cn.yumengfei.controller;

import cn.yumengfei.pojo.Emp;
import cn.yumengfei.response.Result;
import cn.yumengfei.service.EmpService;
import cn.yumengfei.service.impl.EmpServiceA;
import cn.yumengfei.utils.XmlParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    //业务层对象
    @Autowired // IOC容器自动注入EmpService类型的对象
    @Qualifier("empServiceA")
    private EmpService empService;


    /*@RequestMapping("/listEmp")
    public Result list() {
        //1. 加载并解析emp.xml
        String file = this.getClass().getClassLoader().getResource("static/emp.xml").getFile();

        //2. 对数据进行转换处理 - gender, job
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);

        //2. 对数据进行转换处理 - gender, job
        empList.stream().forEach(emp -> {
            //处理 gender 1: 男, 2: 女
            String gender = emp.getGender();
            if("1".equals(gender)){
                emp.setGender("男");
            }else if("2".equals(gender)){
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

        //3. 响应数据
        return Result.success(empList);
    }*/

    @RequestMapping("/listEmp")
    public Result list() {
        //1. 调用service层, 获取数据
        List<Emp> empList = empService.listEmp();

        //3. 响应数据
        return Result.success(empList);
    }
}
