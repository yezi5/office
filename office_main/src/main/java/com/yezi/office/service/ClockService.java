package com.yezi.office.service;

import com.yezi.office.pojo.Clock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 叶子
 * @since 2020-12-29
 */
public interface ClockService extends IService<Clock> {

    /**
     * 打卡签到
     *
     * 返回值：
     *      0   不在规定打卡时间，打卡失败
     *      1   成功
     *      2   已经打卡，无需再次打卡
     *      3   打卡失败
     * @param userId
     * @return
     */
    int signIn(String userId);

    /**
     * 判断当天是否已经打卡/签退
     * 返回值：
     *      true，已经打卡
     *      false，未打卡
     *
     * @param startTime
     * @param endTime
     * @return
     */
    boolean isSign(Date startTime, Date endTime, String userId);

    /**
     * 签退
     * 返回值：
     *      0   不在规定签退时间，签退失败
     *      1   签退成功
     *      2   已经签退，无需再次签退
     *      3   签退失败
     * @param userId
     * @return
     */
    int signOut(String userId);
}
