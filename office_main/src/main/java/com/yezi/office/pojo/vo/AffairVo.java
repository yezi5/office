package com.yezi.office.pojo.vo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.yezi.office.pojo.Affair;
import com.yezi.office.utils.AffairsType;
import lombok.Data;

import java.util.Date;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.vo
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/1 星期五 20:16
 */
public class AffairVo {
    private String affairId;
    private String username;
    /**
     * 事务类型，特殊处理
     */
    private String affairType;
    private String affairReason;
    private String affairAddress;
    /**
     * 开始时间，特殊处理
     */
    private String affairStartTime;
    /**
     * 结束时间，特殊处理
     */
    private String affairEndTime;
    private String affairTime;
    private String affairMoney;
    private Boolean affairIsOk;

    public AffairVo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAffairType() {
        return affairType;
    }

    public void setAffairType(String affairType) {
        this.affairType = affairType;
    }

    public String getAffairReason() {
        return affairReason;
    }

    public void setAffairReason(String affairReason) {
        this.affairReason = affairReason;
    }

    public String getAffairAddress() {
        return affairAddress;
    }

    public void setAffairAddress(String affairAddress) {
        this.affairAddress = affairAddress;
    }


    public String getAffairTime() {
        return affairTime;
    }

    public void setAffairTime(String affairTime) {
        this.affairTime = affairTime;
    }

    public String getAffairMoney() {
        return affairMoney;
    }

    public void setAffairMoney(String affairMoney) {
        this.affairMoney = affairMoney;
    }

    public Boolean getAffairIsOk() {
        return affairIsOk;
    }

    public void setAffairIsOk(Integer affairIsOk) {
        if (affairIsOk == 1){
            this.affairIsOk = true;
        }
        if (affairIsOk == 0){
            this.affairIsOk = false;
        }
    }

    public String getAffairStartTime() {
        return affairStartTime;
    }

    public void setAffairStartTime(Date affairStartTime) {
        this.affairStartTime = DateUtil.format(affairStartTime,"yyyy-MM-dd HH:mm:ss");
    }

    public String getAffairEndTime() {
        return affairEndTime;
    }

    public void setAffairEndTime(Date affairEndTime) {
        this.affairEndTime = DateUtil.format(affairEndTime,"yyyy-MM-dd HH:mm:ss");;
    }

    @Override
    public String toString() {
        return "AffairVo{" +
                "username='" + username + '\'' +
                ", affairType='" + affairType + '\'' +
                ", affairReason='" + affairReason + '\'' +
                ", affairAddress='" + affairAddress + '\'' +
                ", affairStartTime=" + affairStartTime +
                ", affairEndTime=" + affairEndTime +
                ", affairTime=" + affairTime +
                ", affairMoney='" + affairMoney + '\'' +
                ", affairIsOk='" + affairIsOk + '\'' +
                '}';
    }

    public String getAffairId() {
        return affairId;
    }

    public void setAffairId(String affairId) {
        this.affairId = affairId;
    }
}
