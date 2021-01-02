package com.yezi.office.pojo.para;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.para
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/1 星期五 20:31
 */
@Data
public class AffairQuery {

    private String username;
    private String affairType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date affairStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date affairEndTime;
    private Integer affairIsOk;
    private Integer pageIndex;
    private Integer pageSize;
}
