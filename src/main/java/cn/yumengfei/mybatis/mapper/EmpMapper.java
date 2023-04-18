package cn.yumengfei.mybatis.mapper;

import cn.yumengfei.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.*;

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


    @Update("update emp set username=#{username}, password=#{password}, name=#{name}, gender=#{gender}, image=#{image}, job=#{job}, entrydate=#{entrydate}, dept_id=#{deptId}, update_time=#{updateTime}" +
            " where id = #{id}")
    public void update(Emp emp);

    @Select("select username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from emp where id=#{id}")
    public Emp getById(Integer id);
}
