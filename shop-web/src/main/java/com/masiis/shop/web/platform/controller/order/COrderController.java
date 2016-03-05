package com.masiis.shop.web.platform.controller.order;

import com.masiis.shop.dao.beans.product.Product;
import com.masiis.shop.dao.po.PfUserTrial;
import com.masiis.shop.web.platform.controller.base.BaseController;
import com.masiis.shop.web.platform.service.order.COrderService;
import com.masiis.shop.web.platform.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by ZhaoLiang on 2016/3/2.
 */
@Controller
@RequestMapping("/corder")
public class COrderController extends BaseController {

    @Resource
    private COrderService cOrderService;
    @Resource
    private ProductService productService;

    /**
     *
     *@return
     */
    @RequestMapping("/index")
    public String toIndex(HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("userId","1");
        return "index";
    }

    /**
     * 跳转到试用申请界面
     * @author  hanzengzhi
     * @date  2016/3/5 13:45
     */
    @RequestMapping("/applyTrialToPage.json")
    public String applyTrialToPage(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "skuId", required = true) Integer skuId,
                                   Model model)throws Exception{
        if (StringUtils.isEmpty(skuId)){
        }
        Product productDetails = productService.getSkuDetails("1");
        model.addAttribute("product",productDetails);
        return "platform/order/shiyong";
    }
    /**
     * 试用申请
     * @author  hanzengzhi
     * @date  2016/3/5 13:46
     */
    @RequestMapping("/trialApply.json")
    @ResponseBody
    public String trialApply(
            HttpServletRequest request,
            @RequestParam(value = "skuId", required = true) Integer skuId,
            @RequestParam(value = "spuId", required = true) Integer spuId,
            @RequestParam(value = "applyReason", required = false) String applyReason,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "wechat", required = true) String wechat
    ) {
        if (StringUtils.isEmpty(skuId)){

        }
        if (StringUtils.isEmpty(spuId)){

        }
        if (StringUtils.isEmpty(name)){

        }
        if (StringUtils.isEmpty(phone)){

        }
        if (StringUtils.isEmpty(wechat)){

        }
        PfUserTrial pfUserTrial = new PfUserTrial();
        pfUserTrial.setId(1L);
        pfUserTrial.setUserId(11111L);
        pfUserTrial.setSkuId(11);
        pfUserTrial.setSpuId(222);
        pfUserTrial.setStatus(0);
        pfUserTrial.setReason(applyReason);
        pfUserTrial.setName(name);
        pfUserTrial.setMobile(phone);
        pfUserTrial.setWeixinId(wechat);
        pfUserTrial.setCreateTime(new Date());
        cOrderService.trialApplyService(pfUserTrial);
        return "";
    }

}
