<%@ page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">--%>
    <%--<title>麦链商城</title>--%>
    <%--<link rel="stylesheet" href="<%=basePath%>static/css/base.css">--%>
    <%--<link rel="stylesheet" href="<%=basePath%>static/css/reset.css">--%>
    <%--<link rel="stylesheet" href="<%=basePath%>static/css/header.css">--%>
    <%--<link rel="stylesheet" href="<%=basePath%>static/css/zhucelianjie.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="wrap">--%>
    <%--<div id="box">--%>
        <%--<header class="xq_header">--%>
            <%--<a href="javascript:window.history.go(-1);"><img src="<%=basePath%>static/images/xq_rt.png" alt=""></a>--%>
            <%--<p>注册链接</p>--%>
        <%--</header>--%>
        <%--<p>--%>
            <%--您有两种方式发展合伙人--%>
        <%--</p>--%>
        <%--<div class="sec1">--%>
            <%--<p>方法一</p>--%>
            <%--<img src="<%=basePath%>static/images/icon_63.png" alt="">--%>
        <%--</div>--%>
        <%--<div class="sec2">--%>
            <%--<p>方法二</p>--%>
            <%--<h1>宣传海报，将海报发到您的朋友圈</h1>--%>
            <%--<img src="<%=basePath%>static/images/asd.JPG" alt="">--%>
            <%--<h3 id="downloadImage">下载海报</h3>--%>
            <%--<button type="button" id="onMenuShareQQ">分享到QQ</button>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--</body>--%>
<%--<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>--%>
<%--<script>--%>
    <%--/*--%>
     <%--* 注意：--%>
     <%--* 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。--%>
     <%--* 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。--%>
     <%--* 3. 常见问题及完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html--%>
     <%--*--%>
     <%--* 开发中遇到问题详见文档“附录5-常见错误及解决办法”解决，如仍未能解决可通过以下渠道反馈：--%>
     <%--* 邮箱地址：weixin-open@qq.com--%>
     <%--* 邮件主题：【微信JS-SDK反馈】具体问题--%>
     <%--* 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。--%>
     <%--*/--%>
    <%--wx.config({--%>
        <%--debug: true,--%>
        <%--appId: '${shareMap.appId}',--%>
        <%--timestamp: ${shareMap.timestamp},--%>
        <%--nonceStr: '${shareMap.nonceStr}',--%>
        <%--signature: '${shareMap.signature}',--%>
        <%--jsApiList: [--%>
            <%--'checkJsApi',--%>
            <%--'onMenuShareTimeline',--%>
            <%--'onMenuShareAppMessage',--%>
            <%--'onMenuShareQQ',--%>
            <%--'onMenuShareWeibo',--%>
            <%--'onMenuShareQZone',--%>
            <%--'hideMenuItems',--%>
            <%--'showMenuItems',--%>
            <%--'hideAllNonBaseMenuItem',--%>
            <%--'showAllNonBaseMenuItem',--%>
            <%--'translateVoice',--%>
            <%--'startRecord',--%>
            <%--'stopRecord',--%>
            <%--'onVoiceRecordEnd',--%>
            <%--'playVoice',--%>
            <%--'onVoicePlayEnd',--%>
            <%--'pauseVoice',--%>
            <%--'stopVoice',--%>
            <%--'uploadVoice',--%>
            <%--'downloadVoice',--%>
            <%--'chooseImage',--%>
            <%--'previewImage',--%>
            <%--'uploadImage',--%>
            <%--'downloadImage',--%>
            <%--'getNetworkType',--%>
            <%--'openLocation',--%>
            <%--'getLocation',--%>
            <%--'hideOptionMenu',--%>
            <%--'showOptionMenu',--%>
            <%--'closeWindow',--%>
            <%--'scanQRCode',--%>
            <%--'chooseWXPay',--%>
            <%--'openProductSpecificView',--%>
            <%--'addCard',--%>
            <%--'chooseCard',--%>
            <%--'openCard'--%>
        <%--]--%>
    <%--});--%>
<%--</script>--%>
<%--<script src="<%=basePath%>static/js/zepto.min.js"></script>--%>
<%--<script src="<%=basePath%>static/js/share.js"> </script>--%>
<%--</html>--%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>麦链商城</title>
    <link rel="stylesheet" href="<%=basePath%>static/css/base.css">
    <link rel="stylesheet" href="<%=basePath%>static/css/reset.css">
    <link rel="stylesheet" href="<%=basePath%>static/css/header.css">
    <link rel="stylesheet" href="<%=basePath%>static/css/zhucelianjie.css">

    <link rel="stylesheet" href="<%=basePath%>static/js/test/style.css">
</head>
<body ontouchstart="">
<div class="wrap">
    <div id="box">
        <header class="xq_header">
            <a href="javascript:window.history.go(-1);"><img src="<%=basePath%>static/images/xq_rt.png" alt=""></a>
            <p>注册链接</p>
        </header>
        <p>
            您有两种方式发展合伙人
        </p>
        <div class="sec1">
            <p>方法一</p>
            <img src="<%=basePath%>static/images/icon_63.png" alt="">
        </div>
        <div class="sec2">
            <p>方法二</p>
            <h1>宣传海报，将海报发到您的朋友圈</h1>
            <img src="<%=basePath%>static/images/asd.JPG" alt="">
            <h3 id="downloadImage2">下载海报</h3>
        </div>
    </div>
</div>



<div class="wxapi_container">
    <div class="wxapi_index_container">
        <ul class="label_box lbox_close wxapi_index_list">
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-basic">基础接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-share">分享接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-image">图像接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-voice">音频接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-smart">智能接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-device">设备信息接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-location">地理位置接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-webview">界面操作接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-scan">微信扫一扫接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-shopping">微信小店接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-card">微信卡券接口</a></li>
            <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-pay">微信支付接口</a></li>
        </ul>
    </div>
    <div class="lbox_close wxapi_form">
        <h3 id="menu-basic">基础接口</h3>
        <span class="desc">判断当前客户端是否支持指定JS接口</span>
        <button class="btn btn_primary" id="checkJsApi">checkJsApi</button>

        <h3 id="menu-share">分享接口</h3>
        <span class="desc">获取“分享到朋友圈”按钮点击状态及自定义分享内容接口</span>
        <button class="btn btn_primary" id="onMenuShareTimeline">onMenuShareTimeline</button>
        <span class="desc">获取“分享给朋友”按钮点击状态及自定义分享内容接口</span>
        <button class="btn btn_primary" id="onMenuShareAppMessage">onMenuShareAppMessage</button>
        <span class="desc">获取“分享到QQ”按钮点击状态及自定义分享内容接口</span>
        <button class="btn btn_primary" id="onMenuShareQQ">onMenuShareQQ</button>
        <span class="desc">获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口</span>
        <button class="btn btn_primary" id="onMenuShareWeibo">onMenuShareWeibo</button>
        <span class="desc">获取“分享到QZone”按钮点击状态及自定义分享内容接口</span>
        <button class="btn btn_primary" id="onMenuShareQZone">onMenuShareQZone</button>


    </div>
</div>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
    /*
     * 注意：
     * 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
     * 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
     * 3. 常见问题及完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
     *
     * 开发中遇到问题详见文档“附录5-常见错误及解决办法”解决，如仍未能解决可通过以下渠道反馈：
     * 邮箱地址：weixin-open@qq.com
     * 邮件主题：【微信JS-SDK反馈】具体问题
     * 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。
     */
    wx.config({
        debug: true,
        appId: '${shareMap.appId}',
        timestamp: ${shareMap.timestamp},
        nonceStr: '${shareMap.nonceStr}',
        signature: '${shareMap.signature}',
        jsApiList: [
            'checkJsApi',
            'onMenuShareTimeline',
            'onMenuShareAppMessage',
            'onMenuShareQQ',
            'onMenuShareWeibo',
            'onMenuShareQZone',
            'hideMenuItems',
            'showMenuItems',
            'hideAllNonBaseMenuItem',
            'showAllNonBaseMenuItem',
            'translateVoice',
            'startRecord',
            'stopRecord',
            'onVoiceRecordEnd',
            'playVoice',
            'onVoicePlayEnd',
            'pauseVoice',
            'stopVoice',
            'uploadVoice',
            'downloadVoice',
            'chooseImage',
            'previewImage',
            'uploadImage',
            'downloadImage',
            'getNetworkType',
            'openLocation',
            'getLocation',
            'hideOptionMenu',
            'showOptionMenu',
            'closeWindow',
            'scanQRCode',
            'chooseWXPay',
            'openProductSpecificView',
            'addCard',
            'chooseCard',
            'openCard'
        ]
    });
</script>
<script src="<%=basePath%>static/js/zepto.min.js"></script>
<script src="<%=basePath%>static/js/share.js"> </script>
</html>

