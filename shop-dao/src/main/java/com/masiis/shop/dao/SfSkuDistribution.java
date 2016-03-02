package com.masiis.shop.dao;

import java.math.BigDecimal;

public class SfSkuDistribution {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sf_sku_distribution.id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sf_sku_distribution.sku_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Integer skuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sf_sku_distribution.sort
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sf_sku_distribution.discount
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private BigDecimal discount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sf_sku_distribution.remark
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sf_sku_distribution.id
     *
     * @return the value of sf_sku_distribution.id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sf_sku_distribution.id
     *
     * @param id the value for sf_sku_distribution.id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sf_sku_distribution.sku_id
     *
     * @return the value of sf_sku_distribution.sku_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sf_sku_distribution.sku_id
     *
     * @param skuId the value for sf_sku_distribution.sku_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sf_sku_distribution.sort
     *
     * @return the value of sf_sku_distribution.sort
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sf_sku_distribution.sort
     *
     * @param sort the value for sf_sku_distribution.sort
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sf_sku_distribution.discount
     *
     * @return the value of sf_sku_distribution.discount
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sf_sku_distribution.discount
     *
     * @param discount the value for sf_sku_distribution.discount
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sf_sku_distribution.remark
     *
     * @return the value of sf_sku_distribution.remark
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sf_sku_distribution.remark
     *
     * @param remark the value for sf_sku_distribution.remark
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}