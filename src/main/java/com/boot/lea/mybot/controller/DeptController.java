package com.boot.lea.mybot.controller;

import com.boot.lea.mybot.dto.RspDTO;
import com.boot.lea.mybot.entity.Department;
import com.boot.lea.mybot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LiJing
 * @ClassName: DeptController
 * @Description: 部门控制器
 * @date 2019/8/1 15:42
 */

@RestController
@RequestMapping("/dept")
public class DeptController extends AbstractController {

    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * Restful风格
     * 获取部门信息接口
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RspDTO<Department> getDept(@PathVariable("id") Integer id) {
        Department department = departmentMapper.getDepById(id);
        RspDTO<Department> rspDTO = new RspDTO<>();
        return rspDTO.success(department);
    }

    @PostMapping("/save")
    public Department saveDept(Department department) {
        departmentMapper.insertDept(department);
        return department;
    }

}