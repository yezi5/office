package com.yezi.office.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yezi.office.pojo.Bulletin;
import com.yezi.office.mapper.BulletinMapper;
import com.yezi.office.pojo.para.BulletinQuery;
import com.yezi.office.pojo.vo.BulletinVo;
import com.yezi.office.service.BulletinService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yezi.office.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公告表 服务实现类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-02
 */
@Service
public class BulletinServiceImpl extends ServiceImpl<BulletinMapper, Bulletin> implements BulletinService {

    @Autowired
    private UserService userService;
    @Resource
    private BulletinMapper mapper;

    @Override
    public Map<String, Object> list(BulletinQuery query) {
        Map<String, Object> rs = new HashMap<>();
        QueryWrapper<Bulletin> wrapper = new QueryWrapper<>();

        if (!StrUtil.isEmpty(query.getBulletinTitle())){
            wrapper.like("bulletin_title",query.getBulletinTitle());
        }
        if (query.getBulletinIsactive()==1 || query.getBulletinIsactive()==0){
            wrapper.eq("bulletin_isActive",query.getBulletinIsactive());
        }
        if (query.getStartTime()!=null && query.getEndTime()!=null){
            wrapper.ge("gmt_create",query.getStartTime());
            wrapper.le("gmt_create",query.getEndTime());
        }
        if (!StrUtil.isEmpty(query.getUsername())){
            List<String> userIdList = userService.listByUserName(query.getUsername());
            wrapper.in("create_user_id",userIdList);
            wrapper.or().in("update_user_id",userIdList);
        }
        Page<Bulletin> bulletinPage = new Page<>(query.getPageIndex(),query.getPageSize());

        Page<Bulletin> page = baseMapper.selectPage(bulletinPage, wrapper);
        List<BulletinVo> bulletinList = new ArrayList<>();

        for (Bulletin record : page.getRecords()) {
            BulletinVo bulletinVo = new BulletinVo();
            BeanUtils.copyProperties(record,bulletinVo);
            bulletinList.add(bulletinVo);
        }

        rs.put("bulletinList",bulletinList);
        rs.put("pageTotal",page.getTotal());

        return rs;
    }

    @Override
    public int approve(String bulletinId, Integer isActive) {
        return mapper.approve(bulletinId,isActive);
    }
}
