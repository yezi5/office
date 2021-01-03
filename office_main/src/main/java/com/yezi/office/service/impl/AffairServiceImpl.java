package com.yezi.office.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yezi.office.pojo.Affair;
import com.yezi.office.mapper.AffairMapper;
import com.yezi.office.pojo.User;
import com.yezi.office.pojo.para.AffairQuery;
import com.yezi.office.pojo.vo.AffairVo;
import com.yezi.office.service.AffairService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yezi.office.service.UserService;
import com.yezi.office.utils.AffairsType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-01
 */
@Service
public class AffairServiceImpl extends ServiceImpl<AffairMapper, Affair> implements AffairService {

    @Autowired
    private UserService userService;
    @Resource
    private AffairMapper mapper;

    @Override
    public Map<String, Object> find(AffairQuery query) {
        Page<Affair> affairPage = new Page<>(query.getPageIndex(),query.getPageSize());
        QueryWrapper<Affair> wrapper = new QueryWrapper<>();

        if (!StrUtil.isEmpty(query.getUsername())){
            List<String> userIdList = userService.listByUserName(query.getUsername());
            wrapper.in("affair_user_id",userIdList);
        }
        if (query.getAffairStartTime() != null){
            wrapper.ge("affair_start_time",query.getAffairStartTime());
        }
        if (query.getAffairEndTime() != null){
            wrapper.le("affair_end_time",query.getAffairEndTime());
        }
        if (!StrUtil.isEmpty(query.getAffairType())){
            wrapper.eq("affair_type",query.getAffairType());
        }
        if (query.getAffairIsOk() == 0 || query.getAffairIsOk() == 1){
            wrapper.eq("affair_isOk",query.getAffairIsOk());
        }
        Page<Affair> page = page(affairPage, wrapper);
        List<Affair> records = page.getRecords();
        List<AffairVo> list = new ArrayList<>();

        for (Affair affair : records) {
            AffairVo affairVo = new AffairVo();
            BeanUtils.copyProperties(affair,affairVo);
            affairVo.setUsername(userService.getById(affair.getAffairUserId()).getUsername());
            affairVo.setAffairIsOk(affair.getAffairIsok());
            list.add(affairVo);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("affairList",list);
        map.put("pageTotal",page.getTotal());
        map.put("affairTypeList", AffairsType.AFFAIR_TYPE_LIST);

        return map;
    }

    @Override
    public boolean approve(String affairId, boolean affairIsOk) {
        Integer isOk = 0;
        if (affairIsOk){
            isOk = 1;
        }
        int count = mapper.approve(affairId, isOk);
        if (count < 0){
            return false;
        }

        return true;
    }

}
