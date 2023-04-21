package cn.yumengfei.cases.controller;

import cn.yumengfei.cases.pojo.Emp;
import cn.yumengfei.cases.pojo.PageBean;
import cn.yumengfei.cases.pojo.Result;
import cn.yumengfei.cases.service.TbEmpService;
import cn.yumengfei.cases.utils.AilOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * 员工控制器
 */
@Slf4j
@RestController
@RequestMapping("tb-emps")
public class TbEmpController {
    @Autowired
    private TbEmpService tbEmpService;

    @Autowired
    private AilOSSUtils ailOSSUtils;

    // 条件分页
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询, 参数：{}, {}, {}, {}, {}, {}", page, pageSize, name, gender, begin, end);

        PageBean pageBean = tbEmpService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    // 批量删除
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        tbEmpService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工， emp: {}", emp);
        tbEmpService.save(emp);
        return Result.success();
    }

    // 本地上传
    @PostMapping("/localUpload")
    public Result localUpload(String username, Integer age, @RequestParam("image")MultipartFile image) throws IOException {
        log.info("文件上传：{}, {}, {}", username, age, image);

        // 获取原始文件名
        String originalFilename = image.getOriginalFilename();

        // 构建新的文件名
        if (originalFilename != null) {
            String extname = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 随机名 + 文件扩展名
            String newFileName = UUID.randomUUID().toString() + extname;
            // 将文件存储在服务器的磁盘目录
            image.transferTo(new File("D:/images/" + newFileName));

            return Result.success();
        }
        return Result.error("上传失败");
    }

    // oss上传
    @PostMapping("upload")
    public Result update(MultipartFile image) throws IOException {
        // 调用阿里云OSS工具类，将上传上来的文件存入阿里云
        String url = ailOSSUtils.upload(image);
        // 将图片上传完成后的url返回，用于浏览器回显展示
        return Result.success(url);
    }

    // 根据id查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Emp emp = tbEmpService.getById(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp) {
        tbEmpService.update(emp);
        return Result.success();
    }
}
