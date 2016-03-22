package com.masiis.shop.web.platform.controller.user;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.masiis.shop.common.util.OSSObjectUtils;
import com.masiis.shop.common.util.PropertiesUtils;
import com.masiis.shop.dao.platform.product.ComAgentLevelMapper;
import com.masiis.shop.dao.platform.product.ComBrandMapper;
import com.masiis.shop.dao.platform.product.ComSkuMapper;
import com.masiis.shop.dao.platform.product.ComSpuMapper;
import com.masiis.shop.dao.platform.user.ComUserMapper;
import com.masiis.shop.dao.platform.user.PfUserCertificateMapper;
import com.masiis.shop.dao.platform.user.PfUserSkuMapper;
import com.masiis.shop.dao.po.*;
import com.masiis.shop.web.platform.constants.WxConstants;
import com.masiis.shop.web.platform.controller.base.BaseController;
import com.masiis.shop.web.platform.task.JsapiTicketTask;
import com.masiis.shop.web.platform.utils.SpringRedisUtil;
import com.masiis.shop.web.platform.utils.qrcode.CreateParseCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 发展合伙人
 * Created by cai_tb on 16/3/17.
 */
@Controller
@RequestMapping("/developing")
public class DevelopingController extends BaseController {

    private final static Log log = LogFactory.getLog(DevelopingController.class);

    @Resource
    private ComUserMapper comUserMapper;
    @Resource
    private ComSkuMapper comSkuMapper;
    @Resource
    private ComSpuMapper comSpuMapper;
    @Resource
    private ComBrandMapper comBrandMapper;
    @Resource
    private PfUserSkuMapper pfUserSkuMapper;
    @Resource
    private ComAgentLevelMapper comAgentLevelMapper;
    @Resource
    private PfUserCertificateMapper pfUserCertificateMapper;

    @RequestMapping("/ui")
    public ModelAndView ui(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("platform/user/developing");
        ComUser comUser = null;

        try {
            comUser = getComUser(request);
            PfUserSku userSkuC = new PfUserSku();
            userSkuC.setUserId(comUser.getId());
            List<PfUserSku> pfUserSkus = pfUserSkuMapper.selectByCondition(userSkuC);


            if(pfUserSkus != null && pfUserSkus.size() > 0){

                List<Map<String, Object>> agentMaps = new ArrayList<>();
                for(PfUserSku pus : pfUserSkus){

                    ComAgentLevel comAgentLevel = comAgentLevelMapper.selectByPrimaryKey(pus.getAgentLevelId());
                    ComSku comSku = comSkuMapper.selectById(pus.getSkuId());
                    ComSpu comSpu = comSpuMapper.selectById(comSku.getSpuId());
                    ComBrand comBrand = comBrandMapper.selectById(comSpu.getBrandId());

                    Map<String, Object> agentMap = new HashMap<>();
                    agentMap.put("levelName", comAgentLevel.getName());
                    agentMap.put("skuName", comSku.getName());
                    agentMap.put("skuId", comSku.getId());
                    agentMap.put("brandLogo", comBrand.getLogoUrl());

                    agentMaps.add(agentMap);
                }

                mav.addObject("agentMaps", agentMaps);

                return mav;
            }
        } catch (Exception e) {
            log.error("获取我代理的产品失败![comUser="+comUser+"]");
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping("/sharelink")
    public ModelAndView shareLink(HttpServletRequest request, HttpServletResponse response, Integer skuId){

        ModelAndView mav = new ModelAndView("platform/user/sharePage");

        try {
            String curUrl = request.getRequestURL().toString()+"?skuId="+skuId;
            String jsapi_ticket = SpringRedisUtil.get("jsapi_ticket", String.class);
            if(jsapi_ticket == null){
                log.info("从redis获取的jsapi_ticket=null");
                jsapi_ticket = new JsapiTicketTask().requestTicket();
            }

            Map<String, String> resultMap = sign(jsapi_ticket, curUrl);

            ComUser comUser = comUserMapper.selectByPrimaryKey(65L); //getComUser(request);
            ComSku comSku = comSkuMapper.selectById(skuId);
            ComSpu comSpu = comSpuMapper.selectById(comSku.getSpuId());
            ComBrand comBrand = comBrandMapper.selectById(comSpu.getBrandId());
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
            String shareLink = basePath + "userApply/apply.shtml?type=1&skuId="+skuId+"&pUserId="+comUser.getId();

            PfUserCertificate puc = new PfUserCertificate();
            puc.setUserId(comUser.getId());
            puc.setSkuId(comSku.getId());
            List<PfUserCertificate> pfUserCertificates = pfUserCertificateMapper.selectByCondition(puc);
            if(pfUserCertificates != null && pfUserCertificates.size() > 0){
                PfUserCertificate pfUserCertificate = pfUserCertificates.get(0);
                if(pfUserCertificate.getPoster() == null){
                    Date date = new Date();
                    String posterName = pfUserCertificate.getCode()+".png";
                    String posterPath = request.getServletContext().getRealPath("/")+"static"+File.separator+posterName;
                    //生成二维码
                    CreateParseCode.createCode(300,300, shareLink, posterPath);
                    //上传二维码到OSS
                    File posterFile = new File(posterPath);
                    OSSObjectUtils.uploadFile("mmshop", posterFile, "static/user/poster/");
                    //删除本地二维码图片
                    posterFile.delete();
                    //保存二维码图片地址
                    pfUserCertificate.setPoster(PropertiesUtils.getStringValue("index_user_poster_url")+posterName);
                    pfUserCertificateMapper.updateByPrimaryKey(pfUserCertificate);
                }
                resultMap.put("poster", pfUserCertificate.getPoster());
            }


            resultMap.put("appId", WxConstants.APPID);
            resultMap.put("shareTitle", "来自合伙人"+comUser.getRealName()+"的邀请");
            resultMap.put("shareDesc", "我在麦链商城合伙"+comSku.getName()+"，赚了不少钱，邀请你也来试试");
            resultMap.put("shareLink", shareLink);
            resultMap.put("shareImg", comBrand.getLogoUrl());

            //TODO

            mav.addObject("shareMap", resultMap);

            return mav;
        } catch (Exception e) {
            log.error("获取分享链接失败![skuId="+skuId+"]");
            e.printStackTrace();
        }

        return null;
    }



    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        log.info("jsapi将要进行签名的[string1="+string1+"]");

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        log.info("jsapi签名结果[ret="+ret+"]");

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

}
