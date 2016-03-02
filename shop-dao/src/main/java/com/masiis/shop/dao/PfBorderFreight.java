package com.masiis.shop.dao;

import java.util.Date;

public class PfBorderFreight {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_border_freight.id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_border_freight.create_time
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_border_freight.pf_corder_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Long pfCorderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_border_freight.ship_man_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Integer shipManId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_border_freight.ship_man_name
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private String shipManName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pf_border_freight.freight
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private String freight;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_border_freight.id
     *
     * @return the value of pf_border_freight.id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_border_freight.id
     *
     * @param id the value for pf_border_freight.id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_border_freight.create_time
     *
     * @return the value of pf_border_freight.create_time
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_border_freight.create_time
     *
     * @param createTime the value for pf_border_freight.create_time
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_border_freight.pf_corder_id
     *
     * @return the value of pf_border_freight.pf_corder_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Long getPfCorderId() {
        return pfCorderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_border_freight.pf_corder_id
     *
     * @param pfCorderId the value for pf_border_freight.pf_corder_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setPfCorderId(Long pfCorderId) {
        this.pfCorderId = pfCorderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_border_freight.ship_man_id
     *
     * @return the value of pf_border_freight.ship_man_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Integer getShipManId() {
        return shipManId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_border_freight.ship_man_id
     *
     * @param shipManId the value for pf_border_freight.ship_man_id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setShipManId(Integer shipManId) {
        this.shipManId = shipManId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_border_freight.ship_man_name
     *
     * @return the value of pf_border_freight.ship_man_name
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public String getShipManName() {
        return shipManName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_border_freight.ship_man_name
     *
     * @param shipManName the value for pf_border_freight.ship_man_name
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setShipManName(String shipManName) {
        this.shipManName = shipManName == null ? null : shipManName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pf_border_freight.freight
     *
     * @return the value of pf_border_freight.freight
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public String getFreight() {
        return freight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pf_border_freight.freight
     *
     * @param freight the value for pf_border_freight.freight
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setFreight(String freight) {
        this.freight = freight == null ? null : freight.trim();
    }
}