package com.yezi.office.controller;


import com.yezi.office.pojo.Bulletin;
import com.yezi.office.pojo.para.BulletinQuery;
import com.yezi.office.pojo.para.BulletinRelease;
import com.yezi.office.service.BulletinService;
import com.yezi.office.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.HtmlUtils;

import java.util.Map;

/**
 * <p>
 * 公告表 前端控制器
 * </p>
 *
 * @author 叶子
 * @since 2021-01-02
 */
@Api("公告接口")
@RestController
@RequestMapping("/office/bulletin")
@CrossOrigin
public class BulletinController {

    @Autowired
    private BulletinService service;

    @ApiOperation("发布公告")
    @PostMapping("/release")
    @Transactional
    public R release(@RequestBody BulletinRelease bulletin){
        System.out.println(bulletin);
        String html = HtmlUtils.htmlEscapeHex(bulletin.getBulletinContext());
        Bulletin bu = new Bulletin();
        bu.setBulletinTitle(bulletin.getBulletinTitle());
        bu.setBulletinContext(html);
        service.save(bu);

        return R.ok();
    }

    @PostMapping("list")
    public R list(@RequestBody BulletinQuery query){
        Map<String, Object> map = service.list(query);

        return R.ok().data(map);
    }

    @GetMapping("approve/{id}/{isActive}")
    public R approve(@PathVariable("id") String id,
                     @PathVariable("isActive") Integer isActive){
        int count = service.approve(id,isActive);

        return R.ok();
    }


}

