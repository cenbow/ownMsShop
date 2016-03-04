package com.masiis.shop.dao.platform.product;


import com.masiis.shop.dao.beans.product.Product;
import com.masiis.shop.dao.po.ComSkuImage;

import java.util.List;

/**
 * Created by ZhaoLiang on 2016/3/2.
 */
public interface ProductMapper {

    Product getSkuDetailsBySkuId(String skuId);

    List<ComSkuImage> getSkuImgById(String skuId);

}