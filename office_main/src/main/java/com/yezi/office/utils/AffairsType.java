package com.yezi.office.utils;

/**
 * @author 叶子
 * @Description 事务类型
 * @PackageName com.yezi.office.utils
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/1 星期五 15:54
 */
public interface AffairsType {
    /**
     * 请假
     */
    static final String LEAVE = "leave";
    /**
     * 出差
     */
    static final String BUSINESS_TRIP = "businessTrip";
    /**
     * 报销
     */
    static final String APPLY_FOR_REIMBURSEMENT = "applyForReimbursement";
    /**
     * 会议室申请
     */
    static final String MEETING_ROOM_APPLICATION = "meetingRoomApplication";
}
