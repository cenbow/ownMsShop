package com.masiis.shop.web.platform.controller.pay.wxpay;

import com.masiis.shop.common.exceptions.BusinessException;
import com.masiis.shop.web.platform.beans.pay.wxpay.CallBackNotifyReq;
import com.masiis.shop.web.platform.beans.pay.wxpay.CallBackNotifyRes;
import com.masiis.shop.web.platform.controller.base.BaseController;
import com.masiis.shop.web.platform.utils.WXBeanUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by lzh on 2016/3/10.
 */
@Controller
@RequestMapping("/wxntfy")
public class WxNotifyController extends BaseController {
    private Logger log = Logger.getLogger(this.getClass());

    @RequestMapping("/orderNtfy")
    @ResponseBody
    public String uniOrderNotify(HttpServletRequest request, HttpServletResponse response){
        XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        CallBackNotifyRes resObj = new CallBackNotifyRes();
        try {
            String requestBody = null;
            try{
                // 获取请求参数
                requestBody = getRequestBody(request);
                log.info("请求参数:" + requestBody);
            } catch (Exception e) {
                String err = "获取请求参数错误:" + e.getMessage();
                log.error(err);
                throw new BusinessException(err);
            }

            CallBackNotifyReq param = null;
            try{
                xStream.processAnnotations(CallBackNotifyReq.class);
                param = (CallBackNotifyReq) xStream.fromXML(requestBody);
            } catch (Exception e) {
                String err = "参数xml解析错误:" + e.getMessage();
                log.error(err);
                throw new BusinessException(err);
            }

            // 对param进行签名验证
            try{
                String sign = WXBeanUtils.toSignString(param);
                if(StringUtils.isBlank(sign)
                        || !sign.equals(param.getSign())){
                    throw new BusinessException("签名错误");
                }
            } catch (Exception e) {
                String err = "签名验证错误:" + e.getMessage();
                log.error(err);
                throw new BusinessException(err);
            }

            // 开始进行订单异步回调通知业务,要进行参数有效性校验
            synchronized (this) {
                // 放到service中处理

            }

            resObj.setReturn_code("SUCCESS");
            resObj.setReturn_code("OK");
        } catch (Exception e) {
            String err = "公众号支付订单异步回调通知错误:" + e.getMessage();
            log.error(err);
            resObj.setReturn_code("FAIL");
            resObj.setReturn_msg(err);
        }

        xStream.processAnnotations(CallBackNotifyRes.class);
        if(StringUtils.isNotBlank(resObj.getReturn_msg())
                && resObj.getReturn_msg().length() > 128){
            // 返回信息不超过128长度
            resObj.setReturn_msg(resObj.getReturn_msg().substring(0, 128));
        }
        String res = xStream.toXML(resObj);
        return res;
    }
}
