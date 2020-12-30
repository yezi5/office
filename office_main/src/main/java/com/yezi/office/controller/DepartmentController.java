package com.yezi.office.controller;


import com.yezi.office.pojo.Department;
import com.yezi.office.pojo.para.Query;
import com.yezi.office.service.DepartmentService;
import com.yezi.office.utils.R;
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
@RestController
@RequestMapping("/office/department")
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/list")
    public R list(@RequestBody Query query){
        Map<String,Object> map = departmentService.pageList(query);

        return R.ok().data(map);
    }

    @GetMapping("getAll")
    public R getAll(){
        List<Department> selectList = departmentService.list();
        return R.ok().data("selectList",selectList);
    }
}

