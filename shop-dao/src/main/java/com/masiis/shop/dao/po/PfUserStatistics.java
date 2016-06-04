/*
 * PfUserStatistics.java
 * Copyright(C) 2014-2016 麦士集团
 * All rights reserved.
 * -----------------------------------------------
 * 2016-06-04 Created
 */
package com.masiis.shop.dao.po;

import java.util.Date;

public class PfUserStatistics {

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
     * 总销售额
     */
    private Long incomeFee;
    /**
     * 总利润
     */
    private Long profitFee;
    /**
     * 总成本
     */
    private Long costFee;
    /**
     * 进货订单数量
     */
    private Integer upOrderCount;
    /**
     * 进货商品数量
     */
    private Integer upProductCount;
    /**
     * 出货订单数量
     */
    private Integer downOrderCount;
    /**
     * 出货商品数量
     */
    private Integer downProductCount;
    /**
     * 提货订单数量
     */
    private Integer takeOrderCount;
    /**
     * 提货商品数量
     */
    private Integer takeProductCount;
    /**
     * 提货金额
     */
    private Long takeFee;
    /**
     * 版本号
     */
    private Long version;
    /**
     * 备注
     */
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
    public Long getIncomeFee() {
        return incomeFee;
    }
    public void setIncomeFee(Long incomeFee) {
        this.incomeFee = incomeFee;
    }
    public Long getProfitFee() {
        return profitFee;
    }
    public void setProfitFee(Long profitFee) {
        this.profitFee = profitFee;
    }
    public Long getCostFee() {
        return costFee;
    }
    public void setCostFee(Long costFee) {
        this.costFee = costFee;
    }
    public Integer getUpOrderCount() {
        return upOrderCount;
    }
    public void setUpOrderCount(Integer upOrderCount) {
        this.upOrderCount = upOrderCount;
    }
    public Integer getUpProductCount() {
        return upProductCount;
    }
    public void setUpProductCount(Integer upProductCount) {
        this.upProductCount = upProductCount;
    }
    public Integer getDownOrderCount() {
        return downOrderCount;
    }
    public void setDownOrderCount(Integer downOrderCount) {
        this.downOrderCount = downOrderCount;
    }
    public Integer getDownProductCount() {
        return downProductCount;
    }
    public void setDownProductCount(Integer downProductCount) {
        this.downProductCount = downProductCount;
    }
    public Integer getTakeOrderCount() {
        return takeOrderCount;
    }
    public void setTakeOrderCount(Integer takeOrderCount) {
        this.takeOrderCount = takeOrderCount;
    }
    public Integer getTakeProductCount() {
        return takeProductCount;
    }
    public void setTakeProductCount(Integer takeProductCount) {
        this.takeProductCount = takeProductCount;
    }
    public Long getTakeFee() {
        return takeFee;
    }
    public void setTakeFee(Long takeFee) {
        this.takeFee = takeFee;
    }
    public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}