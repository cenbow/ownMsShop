<%@ page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
   <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"> 
    <title>麦链商城</title>
    <link rel="stylesheet" href="<%=path%>/static/css/pageCss/base.css">
    <link rel="stylesheet" href="<%=path%>/static/css/pageCss/reset.css">
    <link rel="stylesheet" href="<%=path%>/static/css/pageCss/zhifudingdan.css">
</head>
<script src="<%=path%>/static/js/plugins/jquery-1.8.3.min.js"></script>
<script>
    function callWeChatPay(){
        var orderCode = $("#orderCodeId").val();
        var orderId = $("#orderId").val();
        window.location.href = "<%=path%>/orderPay/callWechatPay.do?orderCode="+orderCode+"&orderId="+orderId;
    }
</script>
<body>
    <header>
              <a href="index.html"><img src="<%=path%>/static/images/xq_rt.png" alt=""></a>
                <p>付款详情</p>            
        </header>
        <div class="wrap">
                   <main>
                       <input id="orderCodeId" value="${orderCode}" style="display: none">
                       <input id="orderId" value="${orderId}" style="display: none">
                       <c:forEach items="${shopCartSkuDetails}" var="skuDetail">
                        <p>
                            商品信息：<span>${skuDetail.comSku.name}</span>
                        </p>
                       </c:forEach>
                       <p>　需付款：<span>${totalPrice}</span></p>
                    </main>
                   <button onclick="callWeChatPay()">微信支付</button>
        </div>
</body>
</html>