package cn.yumengfei.cases.controller;

import cn.yumengfei.cases.pojo.Dept;
import cn.yumengfei.cases.pojo.Result;
import cn.yumengfei.cases.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/")
    public Result list() {
        log.info("查询所有部门");
        List<Dept> list = deptService.list();
        return Result.success(list);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除部门, {}", id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping("/")
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门: {}", dept);
        deptService.add(dept);
        return Result.success();
    }
}
