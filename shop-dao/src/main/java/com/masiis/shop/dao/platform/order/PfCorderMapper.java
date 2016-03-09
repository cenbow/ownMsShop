package com.masiis.shop.dao.platform.order;


import com.masiis.shop.dao.po.PfCorder;
import com.masiis.shop.dao.po.PfCorderOperationLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 49134 on 2016/3/3.
 */
public interface PfCorderMapper {
    /**
     * 添加一条记录
     * @param pfCorder
     */
    void insert(PfCorder pfCorder);

    /**
     * 根据id更新一条记录
     * @param pfUserTrial
    void updateById(@Param("pfUserTrial") PfUserTrial pfUserTrial);

    *//**
     * 根据id删除一条记录
     * @param
     *//*
    void deleteById(@Param("id") Long id);

    void reason(@Param("pfUserTrial") PfUserTrial pfUserTrial);*/

    List<PfCorder> queryPfCorderByParam(@Param("pfCorder") PfCorder pfCorder);

    void insertPfCorderOperationLog(@Param("pcol")PfCorderOperationLog pcol);
}