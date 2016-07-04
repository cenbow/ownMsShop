package com.masiis.shop.web.mall.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.masiis.shop.common.exceptions.BusinessException;
import com.masiis.shop.dao.mall.shop.SfShopMapper;
import com.masiis.shop.dao.mall.shop.SfShopShoutLogMapper;
import com.masiis.shop.dao.mallBeans.SfShopDetail;
import com.masiis.shop.dao.po.*;
import com.masiis.shop.web.mall.controller.base.BaseController;
import com.masiis.shop.web.mall.service.product.SkuBackGroupImageService;
import com.masiis.shop.web.mall.service.product.SkuImageService;
import com.masiis.shop.web.mall.service.product.SkuService;
import com.masiis.shop.web.mall.service.shop.SfShopService;
import com.masiis.shop.web.mall.service.shop.SfShopSkuService;
import com.masiis.shop.web.mall.service.user.SfUserShopViewService;
import com.masiis.shop.web.mall.service.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Date:2016/4/7
 * @auth:lzh
 */
@Controller
public class IndexController extends BaseController {
    @Resource
    private SfShopMapper sfShopMapper;
    @Resource
    private UserService userService;
    @Resource
    private SfShopService sfShopService;
    @Resource
    private SkuService skuService;
    @Resource
    private SkuImageService skuImageService;
    @Resource
    private SfShopShoutLogMapper sfShopShoutLogMapper;
    @Resource
    private SfUserShopViewService sfUserShopViewService;
    @Resource
    private SkuBackGroupImageService skuBackGroupImageService;

    @RequestMapping("/{shopId}/{userPid}/shop.shtml")
    public ModelAndView index(HttpServletRequest req,
                              @PathVariable("shopId") Long shopId,
                              @PathVariable("userPid") Long userPid) throws Exception {
        ComUser user = getComUser(req);
        if (user == null) {
            throw new BusinessException("user不能为空");
        }

        if(req.getSession().getAttribute("userPid")==null){
            req.getSession().setAttribute("userPid", userPid);
        }else{
            req.getSession().removeAttribute("userPid");
            req.getSession().setAttribute("userPid", userPid);
        }
        if(req.getSession().getAttribute("shopId")==null){
            req.getSession().setAttribute("shopId", shopId);
        }else{
            req.getSession().removeAttribute("shopId");
            req.getSession().setAttribute("shopId", shopId);
        }


//        userService.getShareUser(user.getId(), userPid);//分销关系
        ComUser pUser = userService.getUserById(userPid);

        sfUserShopViewService.addShopView(user.getId(), shopId);
        Integer countByShopId = sfUserShopViewService.findCountByShopId(shopId);//浏览量
        SfShop sfShop = null;
        List<SfShopSku> sfShopSkus = null;
        if (shopId == null) {
            throw new BusinessException("shopId不能为空");
        } else {
            sfShop = sfShopService.getSfShopById(shopId);
            if (sfShop == null) {
                throw new BusinessException("进入方式异常，请联系管理员");
            }
            sfShopSkus = skuService.getSfShopSkuByShopId(shopId);
        }

//        List<SfShopDetail> SfShopDetails = new ArrayList<>();
//        for (SfShopSku sfShopSku : sfShopSkus) {
//                ComSku comSku = skuService.getComSkuBySkuId(sfShopSku.getSkuId());
//                ComSpu comSpu = skuService.getSpuById(comSku.getSpuId());
//                ComSkuImage comSkuImage = skuService.findDefaultComSkuImage(sfShopSku.getSkuId());
//                ComSkuExtension comSkuExtension = skuBackGroupImageService.backGroupImage(sfShopSku.getSkuId());
//                SfShopDetail sfShopDetail = new SfShopDetail();
//                sfShopDetail.setSkuImageUrl(comSkuImage.getFullImgUrl());
//                sfShopDetail.setSkuUrl(comSkuExtension.getSkuBackgroundImg());
//                sfShopDetail.setSkuName(comSku.getName());
//                sfShopDetail.setSkuAssia(comSku.getAlias());
//                sfShopDetail.setPriceRetail(comSku.getPriceRetail());//销售价
//                SfShopSku sfSkuLevelImage = skuService.findSfSkuLevelImage(shopId, sfShopSku.getSkuId());
//                sfShopDetail.setIcon(sfSkuLevelImage.getIcon());//商品代理图标
//                sfShopDetail.setSkuId(comSku.getId());
//                sfShopDetail.setSlogan(comSpu.getSlogan());//一句话介绍
//
//                SfShopDetails.add(sfShopDetail);
//        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pUser", pUser);
        modelAndView.addObject("user", user);
        modelAndView.addObject("countByShopId", countByShopId);
        modelAndView.addObject("userPid", userPid);
        modelAndView.addObject("sfShop", sfShop);
//        modelAndView.addObject("SfShopDetails", SfShopDetails);
        modelAndView.setViewName("newshouye");
        return modelAndView;
    }

    @RequestMapping("/product.do")
    @ResponseBody
    public  List<SfShopDetail> findProduct(HttpServletRequest req){
        List<SfShopDetail> SfShopDetails = new ArrayList<>();
        Long shopId = (Long) req.getSession().getAttribute("shopId");
        try {
            List<SfShopSku> sfShopSkus = skuService.getSfShopSkuByShopId(shopId);
            for (SfShopSku sfShopSku : sfShopSkus) {
                ComSku comSku = skuService.getComSkuBySkuId(sfShopSku.getSkuId());
                ComSpu comSpu = skuService.getSpuById(comSku.getSpuId());
                ComSkuImage comSkuImage = skuService.findDefaultComSkuImage(sfShopSku.getSkuId());
                ComSkuExtension comSkuExtension = skuBackGroupImageService.backGroupImage(sfShopSku.getSkuId());
                SfShopDetail sfShopDetail = new SfShopDetail();
                sfShopDetail.setSkuImageUrl(comSkuImage.getFullImgUrl());
                sfShopDetail.setSkuUrl(comSkuExtension.getSkuBackgroundImg());
                sfShopDetail.setSkuName(comSku.getName());
                sfShopDetail.setSkuAssia(comSku.getAlias());
                sfShopDetail.setPriceRetail(comSku.getPriceRetail());//销售价
                SfShopSku sfSkuLevelImage = skuService.findSfSkuLevelImage(shopId, sfShopSku.getSkuId());
                sfShopDetail.setIcon(sfSkuLevelImage.getIcon());//商品代理图标
                sfShopDetail.setSkuId(comSku.getId());
                sfShopDetail.setSlogan(comSpu.getSlogan());//一句话介绍

                SfShopDetails.add(sfShopDetail);
            }
        }catch (Exception ex){
            if (StringUtils.isNotBlank(ex.getMessage())) {
                throw new BusinessException(ex.getMessage(), ex);
            } else {
                throw new BusinessException("网络错误", ex);
            }
        }
        return SfShopDetails;
    }



    @RequestMapping("/index")
    public ModelAndView myindex(HttpServletRequest req,
                                Long shopId, Long userPid) throws Exception {
        ComUser user = getComUser(req);
        if (user == null) {
            throw new BusinessException("user不能为空");
        }
        shopId =336L;
        userPid = 540L;
        req.getSession().setAttribute("userPid", userPid);
        req.getSession().setAttribute("shopId", shopId);

        ComUser pUser = userService.getUserById(userPid);
        sfUserShopViewService.addShopView(user.getId(), shopId);
        Integer countByShopId = sfUserShopViewService.findCountByShopId(shopId);//浏览量
        SfShop sfShop = null;
        if (shopId == null) {
            throw new BusinessException("shopId不能为空");
        } else {
            sfShop = sfShopService.getSfShopById(shopId);
            if (sfShop == null) {
                throw new BusinessException("进入方式异常，请联系管理员");
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pUser", pUser);
        modelAndView.addObject("user", user);
        modelAndView.addObject("countByShopId", countByShopId);
        modelAndView.addObject("userPid", userPid);
        modelAndView.addObject("sfShop", sfShop);
        modelAndView.setViewName("newshouye");
        return modelAndView;
    }

    /**
     * 呐喊
     *
     * @author muchaofeng
     * @date 2016/4/12 17:05
     */

    @RequestMapping("/shout.do")
    @ResponseBody
    public String shout(HttpServletRequest req, Long shopId) {
        JSONObject json = new JSONObject();
        ComUser user = getComUser(req);
        if (user == null) {
            user = userService.getUserById(1l);
            req.getSession().setAttribute("comUser", user);
        }
        try {
            boolean mallShout = sfShopService.mallShout(user.getId(), shopId);
            SfShop sfShop = null;
            List num = new LinkedList();
            if (mallShout) {
                sfShop = sfShopMapper.selectByPrimaryKey(shopId);
                sfShop.setShoutNum(sfShop.getShoutNum() + 1);

                SfShopShoutLog sfShopShoutLog = new SfShopShoutLog();
                sfShopShoutLog.setCreateTime(new Date());
                sfShopShoutLog.setNum(1);
                sfShopShoutLog.setUserId(user.getId());
                sfShopShoutLog.setShopId(shopId);
                sfShopShoutLog.setShopUserId(sfShop.getUserId());

                sfShopMapper.updateByPrimaryKey(sfShop);
                sfShopShoutLogMapper.insert(sfShopShoutLog);

                //呐喊人数
                String shoutNum= String.valueOf(sfShop.getShoutNum());
                Long moth= sfShop.getShoutNum();
                int length = shoutNum.length();
                if(length>5){
                    for (int i=0;i<length;i++) {
                        num.add(i,0);
                    }
                    for (int i=length-1;i>=0;i--) {
                        String s = String.valueOf(moth % 10);
                        num.remove(num.get(i));
                        num.add(i,s);
                        moth=moth/10;
                    }
                }else{
                    for (int i=0;i<5;i++) {
                        num.add(i,0);
                    }
                    for (int i=4;i>=5-length;i--) {
                        String s = String.valueOf(moth % 10);
                        num.remove(num.get(i));
                        num.add(i,s);
                        moth=moth/10;
                    }
                }
            }
            json.put("mallShout", mallShout);
            json.put("num", num);
        } catch (Exception ex) {
            if (StringUtils.isNotBlank(ex.getMessage())) {
                throw new BusinessException(ex.getMessage(), ex);
            } else {
                throw new BusinessException("网络错误", ex);
            }
        }
        return json.toJSONString();
    }


    /**
     * 分享
     *
     * @author muchaofeng
     * @date 2016/4/12 17:05
     */
    @RequestMapping("/share.html")
    public String share(HttpServletRequest req) throws Exception {
        return "mall/shop/fenxiangjihua";
    }


}
