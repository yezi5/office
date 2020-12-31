package com.yezi.office.controller;


import com.yezi.office.pojo.Department;
import com.yezi.office.pojo.para.Query;
import com.yezi.office.service.DepartmentService;
import com.yezi.office.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 叶子
 * @since 2020-12-29
 */
@Api(tags = "部门操作接口")
@RestController
@RequestMapping("/office/department")
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation("查询接口（分页查询+条件查询）")
    @PostMapping("/list")
    public R list(@RequestBody Query query){
        Map<String,Object> map = departmentService.pageList(query);

        return R.ok().data(map);
    }

    @ApiOperation("获取所有部门信息")
    @GetMapping("getAll")
    public R getAll(){
        List<Department> selectList = departmentService.list();
        return R.ok().data("selectList",selectList);
    }
}

