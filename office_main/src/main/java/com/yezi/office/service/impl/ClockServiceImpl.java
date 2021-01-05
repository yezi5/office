package com.yezi.office.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yezi.office.pojo.Clock;
import com.yezi.office.mapper.ClockMapper;
import com.yezi.office.service.ClockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yezi.office.utils.TokenUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 叶子
 * @since 2020-12-29
 */
@Service
public class ClockServiceImpl extends ServiceImpl<ClockMapper, Clock> implements ClockService {

    Date getOrderTime(String order){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String 日期 = now.format(formatter);
        String time = now.format(formatter) + order;

        return DateUtil.parse(time, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public int signIn(String userId) {
        // 打卡开始时间
        Date startTime = getOrderTime(" 8:00:00");
        // 打卡结束时间
        Date endTime = getOrderTime(" 9:00:00");
        // 当前时间
        Date nowTime = DateUtil.parse(DateUtil.now(), "yyyy-MM-dd HH:mm:ss");

        if (isSign(startTime,endTime,userId)){
            return 2;
        }
        if (!(nowTime.after(startTime) && nowTime.before(endTime))){
            return 0;
        }
        Clock clock = new Clock();
        clock.setClockUserId(userId);
        clock.setClockTime(nowTime);
        boolean save = save(clock);
        if (!save){
            return 3;
        }

        return 1;
    }

    @Override
    public boolean isSign(Date startTime, Date endTime, String userId) {
        QueryWrapper<Clock> wrapper = new QueryWrapper<>();
        wrapper.eq("clock_user_id",userId);
        wrapper.between("clock_time",startTime,endTime);
        Clock one = getOne(wrapper);
        if (one == null){
            return false;
        }

        return true;
    }

    @Override
    public int signOut(String userId) {
        System.out.println(userId);
        // 签退开始时间
        Date startTime = getOrderTime(" 20:00:00");
        // 签退结束时间
        Date endTime = getOrderTime(" 23:00:00");
        // 当前时间
        Date nowTime = DateUtil.parse(DateUtil.now(), "yyyy-MM-dd HH:mm:ss");

        if (isSign(startTime,endTime,userId)){
            System.out.println(2);
            return 2;
        }
        if (!(nowTime.after(startTime) && nowTime.before(endTime))){
            System.out.println(0);
            return 0;
        }
        Clock clock = new Clock();
        clock.setClockUserId(userId);
        clock.setClockTime(nowTime);
        boolean save = save(clock);
        if (!save){
            System.out.println(3);
            return 3;
        }

        return 1;
    }


}
