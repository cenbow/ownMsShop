/*
 * PfSkuAgent.java
 * Copyright(C) 2014-2016 麦士集团
 * All rights reserved.
 * -----------------------------------------------
 * 2016-03-30 Created
 */
package com.masiis.shop.dao.po;

import java.math.BigDecimal;

public class PfSkuAgent {

    private Integer id;
    /**
     * SKU主键ID
     */
    private Integer skuId;
    /**
     * 代理等级表ID
     */
    private Integer agentLevelId;
    /**
     * 折扣(例如0.35为35%也就是3.5折)
     */
    private BigDecimal discount;
    /**
     * 保证金
     */
    private BigDecimal bail;
    /**
     * 拿货数量
     */
    private Integer quantity;
    private String remark;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getSkuId() {
        return skuId;
    }
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }
    public Integer getAgentLevelId() {
        return agentLevelId;
    }
    public void setAgentLevelId(Integer agentLevelId) {
        this.agentLevelId = agentLevelId;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    public BigDecimal getBail() {
        return bail;
    }
    public void setBail(BigDecimal bail) {
        this.bail = bail;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}