package com.masiis.shop.dao;

import java.util.Date;

public class ComCategory {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column com_category.id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column com_category.create_time
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column com_category.create_man
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Long createMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column com_category.modify_time
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column com_category.modify_man
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Long modifyMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column com_category.name
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column com_category.level
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Byte level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column com_category.pid
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Integer pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column com_category.sort
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column com_category.is_validate
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private Integer isValidate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column com_category.remark
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column com_category.id
     *
     * @return the value of com_category.id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column com_category.id
     *
     * @param id the value for com_category.id
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column com_category.create_time
     *
     * @return the value of com_category.create_time
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column com_category.create_time
     *
     * @param createTime the value for com_category.create_time
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column com_category.create_man
     *
     * @return the value of com_category.create_man
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Long getCreateMan() {
        return createMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column com_category.create_man
     *
     * @param createMan the value for com_category.create_man
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setCreateMan(Long createMan) {
        this.createMan = createMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column com_category.modify_time
     *
     * @return the value of com_category.modify_time
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column com_category.modify_time
     *
     * @param modifyTime the value for com_category.modify_time
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column com_category.modify_man
     *
     * @return the value of com_category.modify_man
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Long getModifyMan() {
        return modifyMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column com_category.modify_man
     *
     * @param modifyMan the value for com_category.modify_man
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setModifyMan(Long modifyMan) {
        this.modifyMan = modifyMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column com_category.name
     *
     * @return the value of com_category.name
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column com_category.name
     *
     * @param name the value for com_category.name
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column com_category.level
     *
     * @return the value of com_category.level
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Byte getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column com_category.level
     *
     * @param level the value for com_category.level
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setLevel(Byte level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column com_category.pid
     *
     * @return the value of com_category.pid
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column com_category.pid
     *
     * @param pid the value for com_category.pid
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column com_category.sort
     *
     * @return the value of com_category.sort
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column com_category.sort
     *
     * @param sort the value for com_category.sort
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column com_category.is_validate
     *
     * @return the value of com_category.is_validate
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public Integer getIsValidate() {
        return isValidate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column com_category.is_validate
     *
     * @param isValidate the value for com_category.is_validate
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setIsValidate(Integer isValidate) {
        this.isValidate = isValidate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column com_category.remark
     *
     * @return the value of com_category.remark
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column com_category.remark
     *
     * @param remark the value for com_category.remark
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}