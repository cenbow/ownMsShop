/*
 * SfUserBillItemMapper.java
 * Copyright(C) 2014-2016 麦士集团
 * All rights reserved.
 * -----------------------------------------------
 * 2016-04-12 Created
 */
package com.masiis.shop.dao.mall.user;

import com.masiis.shop.dao.po.SfUserBillItem;
import java.util.List;

public interface SfUserBillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SfUserBillItem record);

    SfUserBillItem selectByPrimaryKey(Long id);

    List<SfUserBillItem> selectAll();

    int updateByPrimaryKey(SfUserBillItem record);
}