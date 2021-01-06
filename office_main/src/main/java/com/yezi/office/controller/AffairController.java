package com.yezi.office.controller;


import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.yezi.office.pojo.Affair;
import com.yezi.office.pojo.para.AffairInfo;
import com.yezi.office.pojo.para.AffairQuery;
import com.yezi.office.service.AffairService;
import com.yezi.office.utils.AffairsType;
import com.yezi.office.utils.R;
import com.yezi.office.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Api(tags = "事务控制接口")
@RestController
@RequestMapping("/office/affair")
@CrossOrigin
public class AffairController {

    @Autowired
    private AffairService service;

    @ApiOperation("查询接口 条件查询 + 分页查询")
    @PostMapping("/list")
    public R find(@ApiParam("查询请求参数") @RequestBody AffairQuery query){

        System.out.println(query);
        Map<String, Object> map = service.find(query);

        return R.ok().data(map);
    }

    @ApiOperation("审批接口，改变事务状态")
    @GetMapping("/approve/{id}/{status}")
    public R approve(@ApiParam("事务ID") @PathVariable("id") String affairId,
                     @ApiParam("事务状态 true 批准 false 不批准") @PathVariable("status") boolean affairIsOk){

        boolean rs = service.approve(affairId,affairIsOk);

        return R.ok();
    }

    @GetMapping("listAffairType")
    @ApiOperation("获取所有的事务类型")
    public R listAffairType(){
        List<String> affairTypeList = AffairsType.AFFAIR_TYPE_LIST;
        affairTypeList.remove("");
        return R.ok().data("affairTypeList", affairTypeList);
    }

    @PostMapping("add")
    @ApiOperation("申请事务")
    public R addAffair(@ApiParam("事务详细信息") @RequestBody AffairInfo affairInfo,
                       HttpServletRequest request){
        String userId = TokenUtils.getUserIdByJwtToken(request);

        Affair affair = new Affair();
        BeanUtils.copyProperties(affairInfo,affair);
        affair.setAffairUserId(userId);
        affair.setAffairIsok(0);
        // 计算时长
        long between = DateUtil.between(affairInfo.getAffairStartTime(), affairInfo.getAffairEndTime(), DateUnit.SECOND);
        String affairTime = DateUtil.formatBetween(between, BetweenFormatter.Level.SECOND);
        affair.setAffairTime(affairTime);
        boolean save = service.save(affair);
        if (!save){
            return R.error();
        }

        return R.ok();
    }
}

