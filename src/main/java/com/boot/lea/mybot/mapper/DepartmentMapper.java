package com.boot.lea.mybot.mapper;


import com.boot.lea.mybot.entity.Department;
import org.apache.ibatis.annotations.*;

/**
 * @author LiJing
 * @ClassName: DepartmentMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/8/1 15:39
 */

@Mapper
public interface DepartmentMapper {

    /**
     * 查询部门
     *
     * @param id
     * @return
     */
    @Select("select * from t_department where id=#{id}")
    Department getDepById(Integer id);

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @Delete("delete from t_department where id=#{id}")
    int deleteDepById(Integer id);

    /**
     * 保存部门
     *
     * @param department
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into t_department(department_name) values(#{departmentName})")
    int insertDept(Department department);

    /**
     * 更新部门
     *
     * @param department
     * @return
     */
    @Update("update t_department set departmentName=#{department_name} where id=#{id}")
    int updateDept(Department department);
}
