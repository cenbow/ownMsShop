package com.masiis.shop.dao;

import com.masiis.shop.dao.ComCategory;
import com.masiis.shop.dao.ComCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ComCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_category
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int countByExample(ComCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_category
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int deleteByExample(ComCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_category
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Delete({
        "delete from com_category",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_category
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Insert({
        "insert into com_category (id, create_time, ",
        "create_man, modify_time, ",
        "modify_man, name, ",
        "level, pid, sort, ",
        "is_validate, remark)",
        "values (#{id,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{createMan,jdbcType=BIGINT}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{modifyMan,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{level,jdbcType=TINYINT}, #{pid,jdbcType=INTEGER}, #{sort,jdbcType=INTEGER}, ",
        "#{isValidate,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(ComCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_category
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int insertSelective(ComCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_category
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    List<ComCategory> selectByExample(ComCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_category
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Select({
        "select",
        "id, create_time, create_man, modify_time, modify_man, name, level, pid, sort, ",
        "is_validate, remark",
        "from com_category",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ComCategory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_category
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByExampleSelective(@Param("record") ComCategory record, @Param("example") ComCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_category
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByExample(@Param("record") ComCategory record, @Param("example") ComCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_category
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByPrimaryKeySelective(ComCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_category
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Update({
        "update com_category",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_man = #{createMan,jdbcType=BIGINT},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "modify_man = #{modifyMan,jdbcType=BIGINT},",
          "name = #{name,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=TINYINT},",
          "pid = #{pid,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER},",
          "is_validate = #{isValidate,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ComCategory record);
}