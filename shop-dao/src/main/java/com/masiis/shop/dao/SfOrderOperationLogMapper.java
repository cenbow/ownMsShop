package com.masiis.shop.dao;

import com.masiis.shop.dao.SfOrderOperationLog;
import com.masiis.shop.dao.SfOrderOperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface SfOrderOperationLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_operation_log
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int countByExample(SfOrderOperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_operation_log
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int deleteByExample(SfOrderOperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_operation_log
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Delete({
        "delete from sf_order_operation_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_operation_log
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Insert({
        "insert into sf_order_operation_log (id)",
        "values (#{id,jdbcType=BIGINT})"
    })
    int insert(SfOrderOperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_operation_log
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int insertSelective(SfOrderOperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_operation_log
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    List<SfOrderOperationLog> selectByExample(SfOrderOperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_operation_log
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByExampleSelective(@Param("record") SfOrderOperationLog record, @Param("example") SfOrderOperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_operation_log
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByExample(@Param("record") SfOrderOperationLog record, @Param("example") SfOrderOperationLogExample example);
}