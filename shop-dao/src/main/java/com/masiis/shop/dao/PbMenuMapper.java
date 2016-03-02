package com.masiis.shop.dao;

import com.masiis.shop.dao.PbMenu;
import com.masiis.shop.dao.PbMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PbMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pb_menu
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int countByExample(PbMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pb_menu
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int deleteByExample(PbMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pb_menu
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Delete({
        "delete from pb_menu",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pb_menu
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Insert({
        "insert into pb_menu (id, name, ",
        "icon, url, parent_id, ",
        "is_deleted, create_time, ",
        "update_time, rank, ",
        "remark)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{icon,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, #{parentId,jdbcType=BIGINT}, ",
        "#{isDeleted,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{rank,jdbcType=INTEGER}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    int insert(PbMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pb_menu
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int insertSelective(PbMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pb_menu
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    List<PbMenu> selectByExample(PbMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pb_menu
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Select({
        "select",
        "id, name, icon, url, parent_id, is_deleted, create_time, update_time, rank, ",
        "remark",
        "from pb_menu",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    PbMenu selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pb_menu
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByExampleSelective(@Param("record") PbMenu record, @Param("example") PbMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pb_menu
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByExample(@Param("record") PbMenu record, @Param("example") PbMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pb_menu
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    int updateByPrimaryKeySelective(PbMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pb_menu
     *
     * @mbggenerated Wed Mar 02 12:09:53 CST 2016
     */
    @Update({
        "update pb_menu",
        "set name = #{name,jdbcType=VARCHAR},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=BIGINT},",
          "is_deleted = #{isDeleted,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "rank = #{rank,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(PbMenu record);
}