package com.masiis.shop.admin.service.product;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.masiis.shop.admin.beans.product.ProductInfo;
import com.masiis.shop.dao.platform.product.*;
import com.masiis.shop.dao.po.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by cai_tb on 16/3/7.
 */
@Service
public class ProductService {

    @Resource
    private ComSpuMapper comSpuMapper;
    @Resource
    private ComSkuMapper comSkuMapper;
    @Resource
    private PfSkuAgentMapper pfSkuAgentMapper;
    @Resource
    private SfSkuDistributionMapper sfSkuDistributionMapper;
    @Resource
    private ComSkuImageMapper comSkuImageMapper;
    @Resource
    private PfSkuStatisticMapper pfSkuStatisticMapper;
    @Resource
    private PfSkuStockService pfSkuStockService;
    @Resource
    private ComSkuExtensionMapper comSkuExtensionMapper;

    /**
     * 添加商品
     *
     * @param comSpu
     * @param comSku
     * @param pfSkuAgents
     * @param sfSkuDistributions
     */
    public void save(ComSpu comSpu, ComSku comSku, ComSkuExtension comSkuExtension, List<ComSkuImage> comSkuImages, List<PfSkuAgent> pfSkuAgents, List<SfSkuDistribution> sfSkuDistributions) {

        //保存spu
        comSpuMapper.insert(comSpu);

        //保存sku
        comSku.setSpuId(comSpu.getId());
        comSkuMapper.insert(comSku);

        //保存sku扩展表
        comSkuExtension.setSkuId(comSku.getId());
        comSkuExtensionMapper.insert(comSkuExtension);

        //sku统计表
        PfSkuStatistic pfSkuStatistic = new PfSkuStatistic();
        pfSkuStatistic.setSkuId(comSku.getId());
        pfSkuStatistic.setAgentNum(0);
        pfSkuStatistic.setTrialNum(0);
        pfSkuStatisticMapper.insert(pfSkuStatistic);

        //库存量
        PfSkuStock pfSkuStock = new PfSkuStock();
        pfSkuStock.setCreateTime(new Date());
        pfSkuStock.setSkuId(comSku.getId());
        pfSkuStock.setSpuId(comSpu.getId());
        pfSkuStock.setStock(10);
        pfSkuStock.setFrozenStock(0);
        pfSkuStock.setVersion(0);
        pfSkuStockService.insert(pfSkuStock);

        //保存sku图片
        int i = 0;
        for (ComSkuImage comSkuImage : comSkuImages) {
            comSkuImage.setSpuId(comSpu.getId());
            comSkuImage.setSkuId(comSku.getId());
            comSkuImage.setSort(i++);

            comSkuImageMapper.insert(comSkuImage);
        }

        //保存代理分润
        for (PfSkuAgent pfSkuAgent : pfSkuAgents) {
            pfSkuAgent.setSkuId(comSku.getId());
            pfSkuAgentMapper.insert(pfSkuAgent);
        }

        //保存分销分润
        for (SfSkuDistribution sfSkuDistribution : sfSkuDistributions) {
            sfSkuDistribution.setSkuId(comSku.getId());
            sfSkuDistributionMapper.insert(sfSkuDistribution);
        }
    }

    /**
     * 修改商品
     *
     * @param comSpu
     * @param comSku
     * @param pfSkuAgents
     * @param sfSkuDistributions
     */
    public void update(ComSpu comSpu, ComSku comSku, ComSkuExtension comSkuExtension, List<ComSkuImage> comSkuImages, List<PfSkuAgent> pfSkuAgents, List<SfSkuDistribution> sfSkuDistributions) {

        if (comSpu.getId() != null && comSku.getId() != null) {
            //保存spu
            comSpuMapper.updateById(comSpu);

            //保存sku
            comSkuMapper.updateById(comSku);

            if(comSkuExtension != null){
                comSkuExtensionMapper.updateById(comSkuExtension);
            }

            //保存sku图片
            if (comSkuImages != null && comSkuImages.size() > 0) {
                //删除原有的sku图片
                comSkuImageMapper.deleteBySkuId(comSku.getId());
                for (ComSkuImage comSkuImage : comSkuImages) {
                    comSkuImageMapper.insert(comSkuImage);
                }
            }

            //保存代理分润
            if (pfSkuAgents != null) {
                for (PfSkuAgent pfSkuAgent : pfSkuAgents) {
                    pfSkuAgentMapper.updateById(pfSkuAgent);
                }
            }

            //保存分销分润
            if (sfSkuDistributions != null) {
                for (SfSkuDistribution sfSkuDistribution : sfSkuDistributions) {
                    sfSkuDistributionMapper.updateById(sfSkuDistribution);
                }
            }
        }

    }

    /**
     * 商品上下架
     *
     * @param comSpu
     */
    public void putaway(ComSpu comSpu) {
        if (comSpu.getIsSale() == 1) {
            comSpu.setUpTime(new Date());
        } else if (comSpu.getIsSale() == 0) {
            comSpu.setDownTime(new Date());
        }
        comSpuMapper.updateById(comSpu);
    }

    /**
     * 商品列表
     *
     * @param comSku
     * @return
     */
    public Map<String, Object> list(Integer pageNo, Integer pageSize, ComSku comSku) {
        List<ProductInfo> productInfos = new ArrayList<>();

        PageHelper.startPage(pageNo, pageSize, "create_time desc");
        List<ComSku> comSkus = comSkuMapper.selectByCondition(comSku);

        for (ComSku cs : comSkus) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setComSku(cs);
            productInfo.setComSpu(comSpuMapper.selectById(cs.getSpuId()));
            productInfo.setPfSkuStock(pfSkuStockService.selectBySkuId(cs.getId()));


            productInfos.add(productInfo);
        }

        PageInfo<ComSku> pageInfo = new PageInfo<>(comSkus);

        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("total", pageInfo.getTotal());
        pageMap.put("rows", productInfos);

        return pageMap;
    }

    /**
     * 获取sku
     *
     * @param skuId
     * @return
     */
    public ProductInfo findSku(Integer skuId) {
        ProductInfo productInfo = new ProductInfo();

        ComSku comSku = comSkuMapper.selectById(skuId);
        ComSpu comSpu = comSpuMapper.selectById(comSku.getSpuId());
        ComSkuExtension comSkuExtension = comSkuExtensionMapper.selectBySkuId(comSku.getId());
        List<ComSkuImage> comSkuImages = comSkuImageMapper.selectBySkuId(comSku.getId());
        List<PfSkuAgent> pfSkuAgents = pfSkuAgentMapper.selectBySkuId(comSku.getId());
        List<SfSkuDistribution> sfSkuDistributions = sfSkuDistributionMapper.selectBySkuId(comSku.getId());

        productInfo.setComSku(comSku);
        productInfo.setComSpu(comSpu);
        productInfo.setComSkuExtension(comSkuExtension);
        productInfo.setComSkuImages(comSkuImages);
        productInfo.setPfSkuAgents(pfSkuAgents);
        productInfo.setSfSkuDistributions(sfSkuDistributions);

        return productInfo;
    }
}
