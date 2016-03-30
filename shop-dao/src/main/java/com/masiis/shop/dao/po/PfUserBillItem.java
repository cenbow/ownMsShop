/*
 * PfUserBillItem.java
 * Copyright(C) 2014-2016 麦士集团
 * All rights reserved.
 * -----------------------------------------------
 * 2016-03-23 Created
 */
package com.masiis.shop.dao.po;

import java.math.BigDecimal;
import java.util.Date;

public class PfUserBillItem {

    private Long id;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 账单id
     */
    private Long pfUserBillId;
    /**
     * 代理订单id
     */
    private Long pfBorderId;
    /**
     * 订单类型: 0,代理订单; 1,分销订单;
     */
    private Integer orderType;
    /**
     * 订单子类型:0,销售; 1,退货
     */
    private Integer orderSubType;
    /**
     * 订单创建时间
     */
    private Date orderCreateDate;
    /**
     * 订单支付金额
     */
    private BigDecimal orderPayAmount;
    private String remark;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Long getPfUserBillId() {
        return pfUserBillId;
    }
    public void setPfUserBillId(Long pfUserBillId) {
        this.pfUserBillId = pfUserBillId;
    }
    public Long getPfBorderId() {
        return pfBorderId;
    }
    public void setPfBorderId(Long pfBorderId) {
        this.pfBorderId = pfBorderId;
    }
    public Integer getOrderType() {
        return orderType;
    }
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
    public Integer getOrderSubType() {
        return orderSubType;
    }
    public void setOrderSubType(Integer orderSubType) {
        this.orderSubType = orderSubType;
    }
    public Date getOrderCreateDate() {
        return orderCreateDate;
    }
    public void setOrderCreateDate(Date orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }
    public BigDecimal getOrderPayAmount() {
        return orderPayAmount;
    }
    public void setOrderPayAmount(BigDecimal orderPayAmount) {
        this.orderPayAmount = orderPayAmount;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}