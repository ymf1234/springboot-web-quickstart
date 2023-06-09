package cn.yumengfei.mybatis.mapper;

import cn.yumengfei.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * 根据id删除数据
     * @param id 用户id
     */
    @Delete("delete from emp where id = #{id}")
    public void delete(Integer id);

    /**
     *  新增
     * @param emp
     */
    //会自动将生成的主键值，赋值给emp对象的id属性
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username,name,password, gender, image, job, entrydate,dept_id,create_time,update_time) " +
            " values (#{username}, #{name}, #{password}, #{gender}, #{image},#{job}, #{entrydate},#{deptId}, #{createTime}, #{createTime})")
    public void insert(Emp emp);


//    @Update("update emp set username=#{username}, password=#{password}, name=#{name}, gender=#{gender}, image=#{image}, job=#{job}, entrydate=#{entrydate}, dept_id=#{deptId}, update_time=#{updateTime}" +
//            " where id = #{id}")
    public void update(Emp emp);

    @Results({
            @Result(column = "dept_id", property = "deptId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")}
    )
    @Select("select username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from emp where id=#{id}")
//    @Select("select username, password, name, gender, image, job, entrydate, dept_id as deptId, create_time as createTime, update_time as updateTime from emp where id=#{id}")
    public Emp getById(Integer id);

    public List<Emp> list(@Param("name") String name, @Param("gender") Short gender, @Param("begin") LocalDate begin, @Param("end") LocalDate end);

    public void deleteByIds(List<Integer> ids);
}
