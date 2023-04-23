package cn.yumengfei.cases.mapper;

import cn.yumengfei.cases.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface TbEmpMapper {
    /*@Select("select count(1) from tb_emp")
    public Long count();*/

    public List<Emp> list(String name,Short gender,LocalDate begin,LocalDate end);

    // 批量删除
    void delete(List<Integer> ids);

    // 新增员工
    @Insert("insert into tb_emp(username, name, gender, image,job,entrydate,dept_id,create_time,update_time)" +
            " values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time  from tb_emp where id = #{id}")
    public Emp findById(Integer id);

    // 修改员工信息
    public void update(Emp emp);

    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from tb_emp where username = #{username} and password = #{password}")
    public Emp getByUsernameAndPassword(Emp emp);

    // 根据部门id删除部门下的所有员工信息
    @Delete("delete from tb_emp where dept_id = #{id}")
    void deleteByDeptId(Integer id);
}
