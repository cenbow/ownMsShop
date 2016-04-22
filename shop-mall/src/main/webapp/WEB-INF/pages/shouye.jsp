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
    <%--<link rel="stylesheet" href="<%=path%>/static/css/pageCss/loading.css">--%>
    <link rel="stylesheet" href="<%=path%>/static/css/pageCss/shouye.css">
</head>
<body>
<div class="addb">
    <p></p>
    <h1>
        <span>麦链合伙人</span>
        <span>关注可查资金，管理店铺，发展下级</span>
    </h1>
    <label class="add">加关注</label>
</div>
<div class="wrap">
    <c:if test="${userPid!=user.id && userPid != sfShop.userId && userPid !=null}">
    <div class="na">
        <p><img src="${pUser.wxHeadImg}" alt=""></p>
        <h1>
            <span>我是${pUser.wxNkName}，我为麦链商城呐喊！</span>
            <span>跟我一起：呐喊得红包，分享赚佣金</span>
        </h1>
    </div>
        </c:if>
    <div class="header">
        <div>
            <p>${sfShop.name}</p>
            <p>${sfShop.explanation}</p>
            <img id="fenxiang" src="<%=path%>/static/images/fen.png" alt="">
        </div>
        <div>
            <p>
                <span>麦链商城官方认证</span>
                <span>商家已缴纳${bail}保证金</span>
            </p>
            <%--<c:forEach begin="0" end="${size}" step="2" var="i">--%>
            <p><c:forEach items="${SfShopDetails}" begin="0" end="0" var="sf">
                <span style="background:url('<%=path%>/static/images/f.png')no-repeat 0;background-size: 14px 14px;">${sf.icon}${sf.skuName}${sf.agentLevelName}认证</span>
            </c:forEach>
            </p>
            <%--</c:forEach>--%>
        </div>
        <c:if test="${not empty sfShop.logo}"><img src="${sfShop.logo}" alt=""></c:if>
        <c:if test="${empty sfShop.logo}"><img src="<%=path%>/static/images/touxiang.png" alt=""></c:if>
    </div>
    <div class="banner">
        <p  class="shout">
            <span>已有</span>
            <span><em id="nahhan">${sfShop.shoutNum}</em>人</span>
            <span>为ta呐喊</span>
            <img src="<%=path%>/static/images/an.png" alt="">
        </p>
    </div>
    <div class="content">
        <h1>在售商品</h1><c:forEach items="${SfShopDetails}" var="sd">
        <section class="sec1" onclick="javascript:window.location.replace('<%=basePath%>shop/detail.shtml/?skuId=${sd.skuId}&shopId=${sfShop.id}');">
            <p class="photo">
                <img src="${sd.skuUrl}" alt="">
            </p>

            <div>
                <h2>${sd.skuName}</h2>

                <h3>${sd.slogan}</h3>

                <h2>运费：<span><c:if test="${ok==false}">包邮</c:if><c:if test="${ok==true}">${sfShop.shipAmount}</c:if></span><b>￥${sd.priceRetail}</b></h2>

                <p>
                    <button>立即购买</button>
                </p>
            </div>
        </section></c:forEach>
    </div>
    <footer>
        <div>
            <p class="active" onclick="javascript:window.location.replace('<%=basePath%>${shopId}/${userPid}/shop.shtml');">
                <span><img src="<%=path%>/static/images/footer_x%20(3).png" alt=""></span>
                <span>首页</span>
            </p>
            <p onclick="javascript:window.location.replace('<%=basePath%>shop/sharePlan?shopId=${sfShop.id}');">
                <span><img src="<%=path%>/static/images/footer%20(1).png" alt=""></span>
                <span>分享计划</span>
            </p>
            <p onclick="javascript:window.location.replace('<%=path%>/sfOrderManagerController/borderManagement.html');" >
                <span><img src="<%=path%>/static/images/footer%20(2).png" alt=""></span>
                <span>个人中心</span>
            </p>
        </div>
    </footer>
</div>
<div id="ok" class="back_f" style="display: none">
    <h1>呐喊成功！</h1>
    <img src="<%=path%>/static/images/qwe%20(1).png" alt="">
    <p>分享店铺到朋友圈，为您的朋友呐喊，通过您分享的链接产生购买后，您将获得佣金</p>
    <button onclick="javascript:window.location.replace('<%=basePath%>shop/getPoster?shopId=${sfShop.id}');">获取我的专属海报</button>
    <span  id="okCloss">×</span>
</div>
<div id="no" class="back_f" style="display: none">
    <h1>您已呐喊过，请明天再来 </h1>
    <img src="<%=path%>/static/images/qwe%20(1).png" alt="">
    <p>分享店铺到朋友圈，为您的朋友呐喊，通过您分享的链接产生购买后，您将获得佣金</p>
    <button onclick="javascript:window.location.replace('<%=basePath%>shop/getPoster?shopId=${sfShop.id}');">获取我的专属海报</button>
    <span class="close" >×</span>
</div>
<div id="fen" class="back_f" style="display: none">
    <img src="<%=path%>/static/images/qwe%20(1).png" alt="">
    <p>分享店铺到朋友圈，为您的朋友呐喊，通过您分享的链接产生购买后，您将获得佣金</p>
    <button onclick="javascript:window.location.replace('<%=basePath%>shop/getPoster?shopId=${sfShop.id}');">获取我的专属海报</button>
    <span class="close">×</span>
</div>
<div class="back_g">
    <p>关注公众账号查资金，管理店铺，发展下级</p>
    <span class="close">×</span>
    <img src="${path}/static/images/asd.JPG" alt="">
</div>
<div class="back"></div>
<script src="<%=path%>/static/js/plugins/jquery-1.8.3.min.js"></script>
<%--<script src="<%=path%>/static/js/common/definedAlertWindow.js"></script>--%>
<%--<script src="<%=path%>/static/js/common/commonAjax.js"></script>--%>
<script>
    $(".close").on("click",function(){
        $(this).parent().hide();
        $(".back").hide();
    })
    $(".add").on("click",function () {
        $(".back_g").show()
        $(".back").show()
    })
    $("#okCloss").on("click",function(){
        $("#ok").hide();
        $(".back").hide();
//        location.reload(true);
    })
    $("#fenxiang").on("click",function(){
        $("#fen").show();
        $(".back").show();
    })

    var naNum =${sfShop.shoutNum+1};
    $(".shout").on("click",function(){
        var shopId =${sfShop.id};
        $.ajax({
            type:"POST",
            url : "<%=path%>/shout.do",
            data:{shopId:shopId},
            dataType:"Json",
            success:function(data){
                if(data.mallShout){
                    $("#ok").show();
                    $(".back").show();
                    $("#nahhan").html(naNum);
//                    location.reload(true);
                } else{
                    $("#no").show();
                    $(".back").show();
                }
            }
        })
    })
</script>
</body>
</html>