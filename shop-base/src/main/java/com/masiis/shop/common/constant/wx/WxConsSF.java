package com.masiis.shop.common.constant.wx;

import com.masiis.shop.common.util.PropertiesUtils;

/**
 * @Date:2016/4/9
 * @auth:lzh
 */
public class WxConsSF {
    /**
     * API调用签名密钥
     */
    public static final String API_KEY = "6AEEB8CB6153CEAAA0FC8AACF5A10934";
    /**
     * 商户号
     */
    public static final String APP_MCHID = "1329841601";
    /**
     * 企业给用户打款url
     */
    public static final String URL_PAY_USER = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    public static final String PATH_CERT = "WEB-INF/files/apiclient_cert.p12";


    /**
     * 授权页面链接
     */
    public static final String URL_AUTH = "https://open.weixin.qq.com/connect/oauth2/authorize";
    /**
     * appid
     */
    public static final String APPID = PropertiesUtils.getStringValue("wx.conf.APPID"); //"wxd5afa1deb29c6197"; //麦士集团appid"wx7c874d5a102dccef";
    /**
     * appsecret
     */
    public static final String APPSECRET = PropertiesUtils.getStringValue("wx.conf.APPSECRET"); //"d0c6c73cbc769450a554a2623d2c45ea"; //麦士集团appsecret"67f21b9a5df948c9dbf203393d8fb1a1";
    /**
     * 获取accesstoken链接
     */
    public static final String REDIECT_URI_GET_ACCESS_TOKEN = "verify/actk";
    /**
     * 授权页面链接参数
     */
    public static final String RESPONSE_TYPE_AUTH = "code";
    /**
     * 页面确认授权的可选参数
     */
    public static final String SCOPE_AUTH_USRINFO = "snsapi_userinfo";
    /**
     * 静默授权的可选参数
     */
    public static final String SCOPE_AUTH_BASE = "snsapi_BASE";
    /**
     * 授权链接尾部带着部分
     */
    public static final String TAILSTR_AUTH = "#wechat_redirect";
    /**
     * 根据code请求获取access_token的请求链接
     */
    public static final String URL_GET_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /**
     * 检查openid和token的有效性
     */
    public static final String URL_CHECK_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/auth";
    /**
     * 刷新token
     */
    public static final String URL_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    public static final String URL_GET_USERINFO = "https://api.weixin.qq.com/sns/userinfo";
    /**
     * 刷新token的grant_type类型
     */
    public static final String GRANT_TYPE_RFTOKEN = "refresh_token";

    public static final String GRANT_TYPE_ACCESSTOKEN = "authorization_code";

    /**
     * JSSDK:access_token获取地址
     */
    public static final String URL_JSSDK_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * JSSDK:grant_type
     */
    public static final String JSSDK_GRANT_TYPE = "client_credential";
    /**
     * JSSDK:jsapi_ticket获取地址
     */
    public static final String URL_JSSDK_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    /*===============================================================================================================*/
    /**
     * 微信支付签名密钥
     */
    public static final String WX_PAY_SIGN_KEY = PropertiesUtils.getStringValue("wx.conf.WX_PAY_SIGN_KEY"); //"U0SJD1OR4WLA5J8QM9IZAJT5KC4ZLS7D";
    /**
     * 商户号
     */
    public static final String WX_PAY_MCHID = PropertiesUtils.getStringValue("wx.conf.WX_PAY_MCHID"); //"1319531601";
    /**
     * 统一下单url
     */
    public static final String WX_PAY_URL_UNIORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 订单异步回调通知地址
     */
    public static final String WX_PAY_URL_UNIORDER_NOTIFY = PropertiesUtils.getStringValue("wx.conf.WX_PAY_URL_UNIORDER_NOTIFY"); //"http://weixin.masiis.com/wxntfy/orderNtfy";

}