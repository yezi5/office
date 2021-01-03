package com.yezi.office.pojo.para;

import lombok.Data;

import java.util.Date;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.para
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/3 星期日 11:28
 */
@Data
public class BulletinQuery {

    private String bulletinTitle;
    private String username;
    private Date startTime;
    private Date endTime;
    private Integer bulletinIsactive;
    private Integer pageIndex;
    private Integer pageSize;
}
