package com.masiis.shop.dao;

import com.masiis.shop.dao.PfCorder;
import com.masiis.shop.dao.PfCorderExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PfCorderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pf_corder
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int countByExample(PfCorderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pf_corder
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int deleteByExample(PfCorderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pf_corder
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Delete({
        "delete from pf_corder",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pf_corder
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Insert({
        "insert into pf_corder (id, create_time, ",
        "create_man, order_code, ",
        "order_type, user_id, ",
        "user_pid, user_massage, ",
        "supplier_id, modify_time, ",
        "modify_man, receivable_amount, ",
        "order_amount, product_amount, ",
        "ship_amount, pay_amount, ",
        "pay_time, ship_man_id, ",
        "ship_man_name, delivery_time, ",
        "ship_remark, replace_order_id, ",
        "order_status, ship_status, ",
        "pay_status, is_ship, ",
        "is_replace, is_receipt, ",
        "receipt_time, remark)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{createMan,jdbcType=BIGINT}, #{orderCode,jdbcType=VARCHAR}, ",
        "#{orderType,jdbcType=INTEGER}, #{userId,jdbcType=BIGINT}, ",
        "#{userPid,jdbcType=BIGINT}, #{userMassage,jdbcType=VARCHAR}, ",
        "#{supplierId,jdbcType=INTEGER}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{modifyMan,jdbcType=BIGINT}, #{receivableAmount,jdbcType=DECIMAL}, ",
        "#{orderAmount,jdbcType=DECIMAL}, #{productAmount,jdbcType=DECIMAL}, ",
        "#{shipAmount,jdbcType=DECIMAL}, #{payAmount,jdbcType=DECIMAL}, ",
        "#{payTime,jdbcType=TIMESTAMP}, #{shipManId,jdbcType=INTEGER}, ",
        "#{shipManName,jdbcType=VARCHAR}, #{deliveryTime,jdbcType=TIMESTAMP}, ",
        "#{shipRemark,jdbcType=VARCHAR}, #{replaceOrderId,jdbcType=BIGINT}, ",
        "#{orderStatus,jdbcType=INTEGER}, #{shipStatus,jdbcType=INTEGER}, ",
        "#{payStatus,jdbcType=INTEGER}, #{isShip,jdbcType=INTEGER}, ",
        "#{isReplace,jdbcType=INTEGER}, #{isReceipt,jdbcType=INTEGER}, ",
        "#{receiptTime,jdbcType=TIMESTAMP}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(PfCorder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pf_corder
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int insertSelective(PfCorder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pf_corder
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    List<PfCorder> selectByExample(PfCorderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pf_corder
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Select({
        "select",
        "id, create_time, create_man, order_code, order_type, user_id, user_pid, user_massage, ",
        "supplier_id, modify_time, modify_man, receivable_amount, order_amount, product_amount, ",
        "ship_amount, pay_amount, pay_time, ship_man_id, ship_man_name, delivery_time, ",
        "ship_remark, replace_order_id, order_status, ship_status, pay_status, is_ship, ",
        "is_replace, is_receipt, receipt_time, remark",
        "from pf_corder",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    PfCorder selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pf_corder
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByExampleSelective(@Param("record") PfCorder record, @Param("example") PfCorderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pf_corder
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByExample(@Param("record") PfCorder record, @Param("example") PfCorderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pf_corder
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByPrimaryKeySelective(PfCorder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pf_corder
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Update({
        "update pf_corder",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_man = #{createMan,jdbcType=BIGINT},",
          "order_code = #{orderCode,jdbcType=VARCHAR},",
          "order_type = #{orderType,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "user_pid = #{userPid,jdbcType=BIGINT},",
          "user_massage = #{userMassage,jdbcType=VARCHAR},",
          "supplier_id = #{supplierId,jdbcType=INTEGER},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "modify_man = #{modifyMan,jdbcType=BIGINT},",
          "receivable_amount = #{receivableAmount,jdbcType=DECIMAL},",
          "order_amount = #{orderAmount,jdbcType=DECIMAL},",
          "product_amount = #{productAmount,jdbcType=DECIMAL},",
          "ship_amount = #{shipAmount,jdbcType=DECIMAL},",
          "pay_amount = #{payAmount,jdbcType=DECIMAL},",
          "pay_time = #{payTime,jdbcType=TIMESTAMP},",
          "ship_man_id = #{shipManId,jdbcType=INTEGER},",
          "ship_man_name = #{shipManName,jdbcType=VARCHAR},",
          "delivery_time = #{deliveryTime,jdbcType=TIMESTAMP},",
          "ship_remark = #{shipRemark,jdbcType=VARCHAR},",
          "replace_order_id = #{replaceOrderId,jdbcType=BIGINT},",
          "order_status = #{orderStatus,jdbcType=INTEGER},",
          "ship_status = #{shipStatus,jdbcType=INTEGER},",
          "pay_status = #{payStatus,jdbcType=INTEGER},",
          "is_ship = #{isShip,jdbcType=INTEGER},",
          "is_replace = #{isReplace,jdbcType=INTEGER},",
          "is_receipt = #{isReceipt,jdbcType=INTEGER},",
          "receipt_time = #{receiptTime,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(PfCorder record);
}