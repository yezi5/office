package com.yezi.office.controller;


import com.yezi.office.pojo.Bulletin;
import com.yezi.office.pojo.para.BulletinQuery;
import com.yezi.office.pojo.para.BulletinRelease;
import com.yezi.office.service.BulletinService;
import com.yezi.office.utils.R;
import com.yezi.office.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 公告表 前端控制器
 * </p>
 *
 * @author 叶子
 * @since 2021-01-02
 */
@Api(tags = "公告控制接口")
@RestController
@RequestMapping("/office/bulletin")
@CrossOrigin
public class BulletinController {

    @Autowired
    private BulletinService service;

    @ApiOperation("发布公告")
    @PostMapping("/release")
    @Transactional
    public R release(@ApiParam("公告详细信息") @RequestBody BulletinRelease bulletin,
                     HttpServletRequest request){
        String html = HtmlUtils.htmlEscapeHex(bulletin.getBulletinContext());
        String userId = TokenUtils.getUserIdByJwtToken(request);
        Bulletin bu = new Bulletin();
        bu.setBulletinTitle(bulletin.getBulletinTitle());
        bu.setBulletinContext(html);
        bu.setCreateUserId(userId);
        service.save(bu);

        return R.ok();
    }

    @PostMapping("list")
    @ApiOperation("查询接口 分页查询+条件查询")
    public R list(@ApiParam("查村参数") @RequestBody BulletinQuery query){
        Map<String, Object> map = service.list(query);

        return R.ok().data(map);
    }

    @GetMapping("approve/{id}/{isActive}")
    @ApiOperation("改变公告状态")
    public R approve(@ApiParam("公告ID") @PathVariable("id") String id,
                     @ApiParam("公告状态 1激活 0未激活") @PathVariable("isActive") Integer isActive){
        int count = service.approve(id,isActive);

        return R.ok();
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation("删除公告")
    public R delete(@ApiParam("公告ID") @PathVariable("id") String bulletinId){

        service.removeById(bulletinId);
        return R.ok();
    }
}

