package com.yezi.office.controller;


import com.yezi.office.pojo.Affair;
import com.yezi.office.pojo.para.AffairQuery;
import com.yezi.office.service.AffairService;
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
 * @since 2021-01-01
 */
@Api(tags = "事务控制器")
@RestController
@RequestMapping("/office/affair")
@CrossOrigin
public class AffairController {

    @Autowired
    private AffairService service;

    @ApiOperation("查询接口")
    @PostMapping("/list")
    public R find(@RequestBody AffairQuery query){


        Map<String, Object> map = service.find(query);

        return R.ok().data(map);
    }

}

