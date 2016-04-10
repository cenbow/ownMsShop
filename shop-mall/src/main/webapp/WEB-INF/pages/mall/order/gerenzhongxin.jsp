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
    <link rel="stylesheet" href="<%=path%>/static/css/pageCss/gerenzhongxin.css">
</head>
<body>
        <div class="wrap">
                <div class="na">
                   <p><img src="${user.wxHeadImg}" alt=""></p>
                   <h1>
                       <span>${user.wxNkName}</span>
                       <span>您的推荐人是<b>${userPid.realName}</b></span>
                   </h1>
               </div>
                <div class="Xin">
                    <p>我的订单</p>
                    <p><a href="<%=path%>/sfOrderManagerController/stockOrder">查看全部订单></a></p>
                </div>
                <div class="dynmic">
                    <p><a href="<%=path%>/sfOrderManagerController/stockOrder?orderStatus=0">
                        <span><img src="<%=path%>/static/images/fukuan.png" alt=""></span>
                        <span>待付款</span></a>
                    </p>
                    <p><a href="<%=path%>/sfOrderManagerController/stockOrder?orderStatus=7">
                        <span><img src="<%=path%>/static/images/fukuan.png" alt=""></span>
                        <span>待发货</span></a>
                    </p>
                    <p><a href="<%=path%>/sfOrderManagerController/stockOrder?orderStatus=8">
                        <span><img src="<%=path%>/static/images/fukuan.png" alt=""></span>
                        <span>待收货</span></a>
                    </p>
                </div>
                <nav>
                    <ul>
                        <li>
                            <span><img src="<%=path%>/static/images/icon_18.png" alt=""></span>
                        <span>我的佣金</span>
                        </li>
                        <li>
                            <span><img src="<%=path%>/static/images/icon_18.png" alt=""></span>
                        <span>个人信息</span>
                        </li>
                        <li>
                            <span><img src="<%=path%>/static/images/icon_18.png" alt=""></span>
                        <span>浏览过的店铺</span>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <span><img src="<%=path%>/static/images/icon_18.png" alt=""></span>
                            <span>地址管理</span>
                        </li>
                        <li></li>
                        <li></li>
                    </ul>
                </nav>
        </div>
</body>
</html>