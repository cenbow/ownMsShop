<%--
  Created by IntelliJ IDEA.
  User: muchaofeng
  Date: 2016/3/1
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%-- 支付 --%>
<%@ page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>麦链商城</title>
    <link rel="stylesheet" href="<%=path%>/static/css/reset.css">
    <link rel="stylesheet" href="<%=path%>/static/css/header.css">
    <link rel="stylesheet" href="<%=path%>/static/css/zhifu.css">
</head>
<body>
<header class="xq_header">
    <a href="<%=path%>/lo/registration-T"><img src="<%=path%>/static/images/xq_rt.png" alt=""></a>
    <p>申请合伙人</p>
</header>
<main>
    <div class="dingdan">
        <h2>支付订单</h2>
        <p>您已经成功注册麦链合伙人，需要您完成订单支付</p>
    </div>
    <div class="xinxi">
        <p style="color:#999999">1.注册信息</p>
        <p>2.支付订单</p>
        <p>3.支付订单</p>
    </div>
    <div class="xinz">
        <p>新增收货地址</p>
    </div>
    <section class="sec1">

        <img src="<%=path%>/static/images/zhifu_ad.png" alt="">
        <div>
            <a href="#"><h2>收货人：<b>王平</b> <span>18611536163</span></h2></a>
            <a href="#"><p>收货地址： <span>北京市 朝阳区 丰联广场A座809A</span><img src="<%=path%>/static/images/next.png" alt=""></p></a>
        </div>

    </section>
    <section class="sec2">
        <p class="photo">
            <img src="<%=path%>/static/images/shenqing_1.png" alt="">
        </p>
        <div>
            <h2>抗引力——快速瘦脸精华<span>x1000</span></h2>
            <h3>规格：<span>默认</span></h3>
            <p>零售价：<span>￥298</span>　合伙人价：<span>￥238</span></p>
        </div>
    </section>
    <section class="sec3">
        <p>运费<span>300.0</span></p>
        <h1>共<span>800</span>件商品　运费：<span>￥300</span><b>合计：</b><span>￥15500.00</span></h1>
        <p>留言：<input type="text"></p>
    </section>
    <a href="javascript:;" class="weixin">微信支付</a>
    <a href="javascript:;" class="xianxia">线下支付</a>
</main>

</body>
</html>
