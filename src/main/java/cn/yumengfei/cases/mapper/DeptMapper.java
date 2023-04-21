package cn.yumengfei.cases.mapper;

import cn.yumengfei.cases.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select id, name, create_time, update_time from tb_dept")
    public List<Dept> list();

    @Delete("delete from tb_dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into tb_dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);
}
