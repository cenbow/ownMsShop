package com.masiis.shop.dao;

import com.masiis.shop.dao.SfOrderFreight;
import com.masiis.shop.dao.SfOrderFreightExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface SfOrderFreightMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_freight
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int countByExample(SfOrderFreightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_freight
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int deleteByExample(SfOrderFreightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_freight
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Delete({
        "delete from sf_order_freight",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_freight
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Insert({
        "insert into sf_order_freight (id)",
        "values (#{id,jdbcType=BIGINT})"
    })
    int insert(SfOrderFreight record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_freight
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int insertSelective(SfOrderFreight record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_freight
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    List<SfOrderFreight> selectByExample(SfOrderFreightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_freight
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByExampleSelective(@Param("record") SfOrderFreight record, @Param("example") SfOrderFreightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sf_order_freight
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByExample(@Param("record") SfOrderFreight record, @Param("example") SfOrderFreightExample example);
}