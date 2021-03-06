/*
 * SfOrderItem.java
 * Copyright(C) 2014-2016 麦士集团
 * All rights reserved.
 * -----------------------------------------------
 * 2016-04-08 Created
 */
package com.masiis.shop.dao.po;

import com.masiis.shop.dao.mallBeans.SfOrderItemImage;

import java.math.BigDecimal;
import java.util.Date;

public class SfOrderItem extends SfOrderItemImage {

    private Long id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 订单id
     */
    private Long sfOrderId;
    /**
     * spu主键id
     */
    private Integer spuId;
    /**
     * sku主键id
     */
    private Integer skuId;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 销售价格
     */
    private BigDecimal originalPrice;
    /**
     * 购买价格
     */
    private BigDecimal unitPrice;
    /**
     * 分润总金额
     */
    private BigDecimal distributionAmount;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 评论标志位(0未评论1已评论)
     */
    private Integer isComment;
    /**
     * 退换货标志位(0未退换1已退换)
     */
    private Integer isReturn;
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
    public Long getSfOrderId() {
        return sfOrderId;
    }
    public void setSfOrderId(Long sfOrderId) {
        this.sfOrderId = sfOrderId;
    }
    public Integer getSpuId() {
        return spuId;
    }
    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }
    public Integer getSkuId() {
        return skuId;
    }
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }
    public String getSkuName() {
        return skuName;
    }
    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }
    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    public BigDecimal getDistributionAmount() {
        return distributionAmount;
    }
    public void setDistributionAmount(BigDecimal distributionAmount) {
        this.distributionAmount = distributionAmount;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Integer getIsComment() {
        return isComment;
    }
    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }
    public Integer getIsReturn() {
        return isReturn;
    }
    public void setIsReturn(Integer isReturn) {
        this.isReturn = isReturn;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}