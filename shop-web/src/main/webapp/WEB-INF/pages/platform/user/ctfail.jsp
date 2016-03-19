<%@ page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>麦链商城</title>
    <link rel="stylesheet" href="<%=path%>/static/css/base.css">
    <link rel="stylesheet" href="<%=path%>/static/css/reset.css">
    <link rel="stylesheet" href="<%=path%>/static/css/header.css">
    <link rel="stylesheet" href="<%=path%>/static/css/zhengshushibai.css">
    <script src="<%=path%>/static/js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/static/js/iscroll.js"></script>
    <script src="<%=path%>/static/js/ajaxfileupload.js"></script>
    <script>
        var checkImg = 0;
        function F_Open_dialog(data) {
            if (data == 0) {
                checkImg = 0;
            } else {
                checkImg = 1;
            }
            document.getElementById("idCardImg").click();
        }
        function uploadIdCardImg() {
            $.ajaxFileUpload({
                url: "<%=basePath%>userCertificate/idCardImgUpload.do",
                data: "",
                type: "POST",
                secureuri: false,
                fileElementId: ['idCardImg'],
                dataType: "json",
                success: function (rdata) {
                    var data = JSON.parse(rdata);
                    if (data.code == 1) {
                        if (checkImg == 0) {
                            $("#idCardFront").attr("src", "<%=basePath%>" + data.imgPath);
                        } else {
                            $("#idCardBack").attr("src", "<%=path%>" + data.imgPath);
                        }
                    } else {
                        alert(data.msg);
                    }
                }
            });
        }
        function submit() {
            var fCardUrl = $("#idCardFront").attr("src");
            var bCardUrl = $("#idCardBack").attr("src");
            fCardUrl = fCardUrl.substr(fCardUrl.lastIndexOf('/') + 1);
            bCardUrl = bCardUrl.substr(bCardUrl.lastIndexOf('/') + 1);
            var paraData = {};
            paraData.userSkuId = "${ctfaildetail.id}";
            paraData.name = "${comUser.realName}";
            paraData.name = "${ctfaildetail.wxId}";
            paraData.idCard = $("#idCard").val();
            paraData.idCardFrontUrl = fCardUrl;
            paraData.idCardBackUrl = bCardUrl;
            $.ajax({
                url: "<%=basePath%>userCertificate/update.do",
                type: "post",
                data: paraData,
                dataType: "json",
                success: function (data) {
                    if (data.isError == false) {
                        alert(1);
                    }
                    else {
                        alert(data.message);
                    }
                }
            });
        }
    </script>
</head>
<body>
<div class="wrap">
    <header class="xq_header">
        <a href="zhifu.html"><img src="<%=path%>/static/images/xq_rt.png" alt=""></a>
        <p>证书详情</p>
    </header>
    <main>
        <div id="box">
            <div class="sec1">
                <h1>您的资料审核失败了！</h1>
                <p>失败原因：<span>${ctfaildetail.remark}</span></p>
            </div>
            <div class="sec2">
                <p>提交时间：<span>${ctfaildetail.beginTime}</span></p>
                <p>申请产品：<span>${comSku.name}</span></p>
                <p>申请等级：<span>
                    <c:choose>
                    <c:when test="${ctfaildetail.agentLevelId==1}">
                        <em>初级合伙人</em>
                    </c:when>
                    <c:when test="${ctfaildetail.agentLevelId==2}">
                        <em>中级合伙人</em>
                    </c:when>
                    <c:when test="${ctfaildetail.agentLevelId==3}">
                        <em>高级合伙人</em>
                    </c:when>
                </c:choose></span></p>
                <p>付款状态：<span>已付款</span></p>
            </div>
            <div class="sec3">
                <p>申请人：<span><input type="text" id="userName" name="userName" value="${comUser.realName}"></span></p>
                <p>手机号：<span>${ctfaildetail.mobile}</span><label>已验证</label><a href="">更改手机号</a></p>
                <p>微信号：<span><input type="text" id="wxId" name="wxId" value="${ctfaildetail.wxId}"></span></p>
                <p>身份证号：<span><input type="tel" id="idCard" name="idCard" value="${ctfaildetail.idCard}"></span></p>
                <p style="border:none;">身份证扫描件：</p>
                <div class="sfphoto">
                    <input type="file" id="idCardImg" name="idCardImg" onchange="uploadIdCardImg()"
                           style="opacity:0;filter:alpha(opacity=0);height: 95px;width: 100px;position: absolute;top: -50;left: -50;z-index: 9;">
                    <div class="zheng">
                        <img src="${comUser.idCardFrontUrl}" alt="" id="idCardFront" name="idCardPre" >
                        <span>正面</span>
                        <label for="idCardImg" onclick="F_Open_dialog(0)">重新上传</label>
                    </div>
                    <div class="fan" style="margin-left:10px;">
                            <img src="${comUser.idCardBackUrl}" alt="" id="idCardBack" name="idCardPre">
                        <span>反面</span>
                        <label for="idCardImg" onclick="F_Open_dialog(1)">重新上传</label>
                    </div>
                </div>
                <botton class="btn">重新提交</botton>
            </div>
        </div>
    </main>
</div>
</body>
</html>