<%@ page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>麦链合伙人</title>
    <%@ include file="/WEB-INF/pages/common/head.jsp" %>
    <link rel="stylesheet" href="${path}/static/css/zhifu.css">
</head>
<body>
<div class="wrap">
    <div class="box">
        <header class="xq_header">
            <c:choose>
                <c:when test="${previousPageType==0}">
                    <a href="${basePath}userApply/register.shtml?skuId=${bOrderConfirm.skuId}">
                        <img src="${path}/static/images/xq_rt.png" alt=""></a>
                </c:when>
                <c:otherwise>
                    <a href="${basePath}border/setUserSendType.shtml?skuId=${bOrderConfirm.skuId}&agentLevelId=${bOrderConfirm.agentLevelId}&weiXinId=${bOrderConfirm.wenXinId}">
                        <img src="${path}/static/images/xq_rt.png" alt=""></a>
                </c:otherwise>
            </c:choose>
            <p>支付订单</p>
        </header>
        <main>
            <c:choose>
                <c:when test="${comUser.sendType==0}">
                    <div class="xinxi">
                        <p>注册信息</p>
                        <p>选择拿货方式</p>
                        <p>支付订单</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="xinxi two" style="display: -webkit-box;">
                        <p>信息填写</p>
                        <p>支付订单</p>
                        <p>完成合伙</p>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:if test="${isQueuing==true}">
                <div class="paidan">
                    <h1><img src="${path}/static/images/loading.png" alt=""><b>在您前面还有<span>${count}</span>人排单</b></h1>
                    <p style="color: #FF5200">*由于商品火爆导致库存不足,本次申请将进入排单系统,待产能提升,我们会按付款顺序发货</p>
                </div>
            </c:if>
            <c:if test="${bOrderConfirm.sendType==2}">
                <div class="Type">
                    <p>拿货方式：<span>自己发货</span></p>
                </div>
                <div class="xinz" onclick="toChooseAddressPage()">
                    <p><a>选择收货地址</a></p>
                </div>
                <section class="sec1">
                    <img src="${path}/static/images/zhifu_ad.png" alt="">
                    <div onclick="toChooseAddressPage()">
                        <input style="display: none" type="text" id="addressId"
                               value="${bOrderConfirm.comUserAddress.id}"/>
                        <a href="#"><h2>收货人：<b>${bOrderConfirm.comUserAddress.name}</b>
                            <span>${bOrderConfirm.comUserAddress.mobile}</span></h2></a>
                        <a href="#"><p>收货地址：
                            <span>${bOrderConfirm.comUserAddress.provinceName}  ${bOrderConfirm.comUserAddress.cityName}  ${bOrderConfirm.comUserAddress.regionName} ${bOrderConfirm.comUserAddress.address}
                            </span><img src="${path}/static/images/next.png" alt=""></p></a>
                    </div>
                </section>
            </c:if>
            <c:if test="${bOrderConfirm.sendType==1}">
                <div class="Type2">
                    <p>拿货方式：<span>平台代发</span></p>
                    <h1>支付成功后，您的在线库存将会增加</h1>
                </div>
            </c:if>
            <section class="sec2">
                <p class="photo">
                    <img src="${bOrderConfirm.skuImg}" alt=\"\">
                </p>
                <div>
                    <h2> ${bOrderConfirm.skuName}
                        <b style="float:right;margin-right:10px;font-size:12px;">x${bOrderConfirm.skuQuantity}</b>
                    </h2>
                    <h3>合伙人等级：<span>${bOrderConfirm.agentLevelName}</span></h3>
                    <p>商品总价:<span>${bOrderConfirm.productTotalPrice}</span>保证金:<span>${bOrderConfirm.bailAmount}</span>
                    </p>
                </div>
            </section>
            <section class="sec3">
                <p>留言：<input type="text" id="userMessage" name="userMessage"></p>
            </section>
            <div>
                <p><img src="${path}/static/images/lirun.png" alt=""></p>
                <h1>
                    <span>预计您的利润</span>
                    <span>￥${bOrderConfirm.lowProfit}~￥${bOrderConfirm.highProfit}</span>
                </h1>
            </div>
            <section class="sec4">
                <p><b>保证金：</b><span>￥${bOrderConfirm.bailAmount}</span></p>
                <p><b>共需支付：</b><span>￥${bOrderConfirm.orderTotalPrice}</span></p>
            </section>
            <a href="javascript:;" onclick="submit(this);" class="weixin">下一步</a>
        </main>
    </div>
</div>
</body>
<%@ include file="/WEB-INF/pages/common/foot.jsp" %>
<script>
    $(document).ready(function () {
        var addressId = $("#addressId").val();
        if (addressId == "") {
            $(".xinz").show();
            $(".sec1").hide();
        } else {
            $(".xinz").hide();
            $(".sec1").attr("style", "display:-webkit-box;");
        }
    })
    var myScroll = new IScroll(".wrap", {
        preventDefault: false
    })

    function toChooseAddressPage() {
        var selectedAddressId = $("#addressId").val();
        window.location.href = "${path}/userAddress/toChooseAddressPage.html?pageType=agentOrder&selectedAddressId=" + selectedAddressId + "&agentOrderparamForAddress=" + JSON.stringify(${agentOrderparamForAddress});
    }

    function submit(para) {
        if ($(para).html() == "正在提交...") {
            return;
        }
        var sendType =${bOrderConfirm.sendType};
        var orderType =${bOrderConfirm.orderType};
        if (orderType == 2 || sendType == 2 && ($("#addressId").val() == null || $("#addressId").val() == "")) {
            alert("请填写收货地址");
            return;
        }
        var paraData = {};
        paraData.orderType = "${bOrderConfirm.orderType}";
        paraData.sendType = "${bOrderConfirm.sendType}";
        paraData.skuId = "${bOrderConfirm.skuId}";
        paraData.agentLevelId = "${bOrderConfirm.agentLevelId}";
        paraData.weiXinId = "${bOrderConfirm.wenXinId}";
        paraData.userMessage = $("#userMessage").val();
        paraData.userAddressId = $("#addressId").val();
        $.ajax({
            url: "${basePath}BOrderAdd/agentBOrder/add.do",
            type: "post",
            data: paraData,
            dataType: "json",
            success: function (data) {
                if (data.isError == false) {
                    $(para).html("正在提交...");
                    window.location.href = "${basePath}border/goToPayBOrder.shtml?bOrderId=" + data.bOrderId;
                }
            }
        });
    }
</script>
</html>