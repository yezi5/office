package com.yezi.office.pojo.para;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.para
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/5 星期二 20:40
 */
@Data
public class AffairInfo {

    private String affairType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date affairStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date affairEndTime;
    private String affairReason;
    private String affairAddress;
    private String affairMoney;
}
