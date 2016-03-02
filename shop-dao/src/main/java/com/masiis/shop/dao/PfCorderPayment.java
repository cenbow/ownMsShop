package com.masiis.shop.dao;

import java.math.BigDecimal;
import java.util.Date;

public class PfCorderPayment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_corder_payment.id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_corder_payment.create_time
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_corder_payment.pf_corder_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Long pfCorderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_corder_payment.amount
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private BigDecimal amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_corder_payment.pay_type_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Integer payTypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_corder_payment.pay_type_name
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private String payTypeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_corder_payment.is_enabled
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Integer isEnabled;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_corder_payment.remark
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_corder_payment.id
     *
     * @return the value of pf_corder_payment.id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_corder_payment.id
     *
     * @param id the value for pf_corder_payment.id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_corder_payment.create_time
     *
     * @return the value of pf_corder_payment.create_time
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_corder_payment.create_time
     *
     * @param createTime the value for pf_corder_payment.create_time
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_corder_payment.pf_corder_id
     *
     * @return the value of pf_corder_payment.pf_corder_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Long getPfCorderId() {
        return pfCorderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_corder_payment.pf_corder_id
     *
     * @param pfCorderId the value for pf_corder_payment.pf_corder_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setPfCorderId(Long pfCorderId) {
        this.pfCorderId = pfCorderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_corder_payment.amount
     *
     * @return the value of pf_corder_payment.amount
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_corder_payment.amount
     *
     * @param amount the value for pf_corder_payment.amount
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_corder_payment.pay_type_id
     *
     * @return the value of pf_corder_payment.pay_type_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Integer getPayTypeId() {
        return payTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_corder_payment.pay_type_id
     *
     * @param payTypeId the value for pf_corder_payment.pay_type_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setPayTypeId(Integer payTypeId) {
        this.payTypeId = payTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_corder_payment.pay_type_name
     *
     * @return the value of pf_corder_payment.pay_type_name
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public String getPayTypeName() {
        return payTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_corder_payment.pay_type_name
     *
     * @param payTypeName the value for pf_corder_payment.pay_type_name
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName == null ? null : payTypeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_corder_payment.is_enabled
     *
     * @return the value of pf_corder_payment.is_enabled
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Integer getIsEnabled() {
        return isEnabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_corder_payment.is_enabled
     *
     * @param isEnabled the value for pf_corder_payment.is_enabled
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_corder_payment.remark
     *
     * @return the value of pf_corder_payment.remark
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_corder_payment.remark
     *
     * @param remark the value for pf_corder_payment.remark
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}