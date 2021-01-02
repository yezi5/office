package com.yezi.office.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    static final String LEAVE = "请假";
    /**
     * 出差
     */
    static final String BUSINESS_TRIP = "出差";
    /**
     * 报销
     */
    static final String APPLY_FOR_REIMBURSEMENT = "报销";
    /**
     * 会议室申请
     */
    static final String MEETING_ROOM_APPLICATION = "会议室申请";

    @Deprecated
    static final String[] AFFAIR_TYPE_ARRAY = {"",LEAVE,BUSINESS_TRIP,APPLY_FOR_REIMBURSEMENT,MEETING_ROOM_APPLICATION};

    static final List<String> AFFAIR_TYPE_LIST = new ArrayList<>(Arrays.asList(AFFAIR_TYPE_ARRAY));
}
