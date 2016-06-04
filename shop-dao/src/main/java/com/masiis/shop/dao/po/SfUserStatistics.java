/*
 * SfUserStatistics.java
 * Copyright(C) 2014-2016 麦士集团
 * All rights reserved.
 * -----------------------------------------------
 * 2016-06-04 Created
 */
package com.masiis.shop.dao.po;

import java.util.Date;

public class SfUserStatistics {

    private Long id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 总订单数
     */
    private Integer orderCount;
    /**
     * 总分润
     */
    private Long distributionFee;
    /**
     * 累计体现金额
     */
    private Long withdrawFee;
    /**
     * 总购买金额
     */
    private Long buyFee;
    private String remark;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Integer getOrderCount() {
        return orderCount;
    }
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }
    public Long getDistributionFee() {
        return distributionFee;
    }
    public void setDistributionFee(Long distributionFee) {
        this.distributionFee = distributionFee;
    }
    public Long getWithdrawFee() {
        return withdrawFee;
    }
    public void setWithdrawFee(Long withdrawFee) {
        this.withdrawFee = withdrawFee;
    }
    public Long getBuyFee() {
        return buyFee;
    }
    public void setBuyFee(Long buyFee) {
        this.buyFee = buyFee;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}