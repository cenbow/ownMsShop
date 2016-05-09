<%@ page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>麦链商城</title>
    <link rel="stylesheet" href="<%=path%>/static/css/pageCss/base.css">
    <link rel="stylesheet" href="<%=path%>/static/css/pageCss/reset.css">
    <link rel="stylesheet" href="<%=path%>/static/css/pageCss/zhifuchenggong.css">
</head>
<body>
<header>
    <a href="#" onClick="javascript :history.go(-1);"><img src="<%=path%>/static/images/xq_rt.png" alt=""></a>
    <p>订单详情</p>
</header>
<div class="wrap">
    <div class="tai">
        <img src="<%=path%>/static/images/icon_64.png" alt="">
        <h1>支付成功</h1>
        <p>您的订单支付成功，请耐心等待收货</p>
    </div>
    <section class="sec1">

        <img src="<%=path%>/static/images/zhifu_ad.png" alt="">
        <div>
            <a href="#"><h2>收货人：<b>${orderConsignee.consignee}</b> <span>${orderConsignee.mobile}</span></h2></a>
            <a href="#"><p>收货地址： <span>
                ${orderConsignee.provinceName} ${orderConsignee.cityName} ${orderConsignee.regionName} ${orderConsignee.address}
            </span></p></a>
        </div>

    </section>
    <section class="sec2">
        <p class="photo">
            <a>
                <img src="<%=path%>/static/images/shenqing_1.png" alt="">
            </a>
        </p>
        <c:forEach items="${orderItems}" var="orderItem">
            <div>
                <h2>${orderItem.skuName}</h2>
                <h3>规格：<span>默认</span></h3>
                <h3>￥${orderItem.originalPrice}<b>x${orderItem.quantity}</b></h3>

            </div>
        </c:forEach>
    </section>
    <section class="sec3">
        <p>买家留言：${order.userMessage}</p>
    </section>
    <section class="sec4">
        <p><b>合计：</b><span>￥${order.productAmount}</span></p>
        <p><b>运费：</b><span>￥${order.shipAmount}</span></p>
        <p><b>需付：</b><span>￥${order.orderAmount}</span></p>
    </section>
    <div class="back"></div>
    <div class="sec6">
        <p><a onclick="askForInvoice()">返回首页</a></p>
        <p><a onclick="contactSeller()">联系卖家</a></p>
        <p><a onclick="askForInvoice()">索要发票</a></p>
    </div>
</div>
<div id="contactSellerDivId" class="back_l">
    <p>联系卖家</p>
    <p>请联系：00000000</p>
    <button  onclick="closeContactSeller()">知道了</button>
    <span class="close" onclick="closeContactSeller()">×</span>
</div>
<div id="askForInvoiceDivId" class="back_l back_s">
    <p>索要发票</p>
    <p>请联系：00000000</p>
    <button onclick="closeAskForInvoice()">知道了</button>
    <span onclick="closeAskForInvoice()" class="close">×</span>
</div>
<script src="<%=path%>/static/js/plugins/jquery-1.8.3.min.js"></script>
<script>
    function contactSeller(){
        $("#contactSellerDivId").show();
        $(".back").show();
    }
    function closeContactSeller(){
        $("#contactSellerDivId").hide();
        $(".back").hide();
    }
    function askForInvoice(){
        $("#askForInvoiceDivId").show();
        $(".back").show();
    }
    function closeAskForInvoice(){
        $("#askForInvoiceDivId").hide();
        $(".back").hide();
    }
    function closeShare(){
        $("#shareDivId").hide();
        $(".back").hide();
    }
    function returnHomePage(){
        window.location.href="${mallDomainNameAddress}/${order.shopId}/${userPid}/shop.shtml";
    }
</script>
</body>
</html>