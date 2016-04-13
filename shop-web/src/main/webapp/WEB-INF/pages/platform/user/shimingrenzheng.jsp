<%@ page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>麦链合伙人</title>
    <%@ include file="/WEB-INF/pages/common/head.jsp" %>
    <link rel="stylesheet" href="${path}/static/css/tijiaosq.css">
    <link rel="stylesheet" href="${path}/static/css/fakeLoader.css"/>
</head>
<body>
<div class="wrap">
    <div class="box">
        <header class="xq_header">
            <a href="javascript:;" onClick="javascript :history.go(-1);"><img src="${path}/static/images/xq_rt.png"
                                                                              alt=""></a>
            <p>实名认证</p>
        </header>
        <p class="cp">
            <span>实名认证通过后才能申请合伙人</span>
        </p>

        <main>
            <div class="sf">
                姓名：
                <input type="text" id="name" name="name">
            </div>
            <div class="sf">
                身份证号：
                <input type="text" id="idCard" name="idCard">
            </div>
            <div class="sf" style="border-bottom:none;">
                身份证照片：
            </div>
            <div class="sfphoto">
                <input type="file" id="idCardImg" name="idCardImg" onchange="uploadIdCardImg()"
                       style="display: none;">
                <label class="zheng">
                    <div class="fakeloader0"></div>
                    <img src="${path}/static/images/shenfen.png" alt="" id="idCardFront" name="idCardPre"
                         onclick="F_Open_dialog(0)">
                </label>
                <label class="fan" style="margin-left:10px;">
                    <div class="fakeloader1"></div>
                    <img src="${path}/static/images/shenfenf.png" alt="" id="idCardBack" name="idCardPre"
                         onclick="F_Open_dialog(1)">
                </label>
            </div>

        </main>
        <a href="javascript:;" class="tijiao" onclick="submit();">提交申请</a>
    </div>
</div>
</body>
<%@ include file="/WEB-INF/pages/common/foot.jsp" %>
<script src="${path}/static/js/ajaxfileupload.js"></script>
<script src="${path}/static/js/fakeLoader.js"></script>
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
        var selector = !checkImg ? '.zheng':'.fan';
        $(".fakeloader"+checkImg).fakeLoader({
            timeToHide:120000,
            bgColor:"#ececec",
            spinner:"spinner2",
            width: $(selector).width(),
            height: $(selector).height(),
            left: $(selector).position().left,
            top: $(selector).position().top
        });
        $.ajaxFileUpload({
            url: "${path}/userCertificate/idCardImgUpload.do",
            data: "",
            type: "POST",
            secureuri: false,
            fileElementId: ['idCardImg'],
            dataType: "json",
            success: function (rdata) {
                var data = JSON.parse(rdata);
                if (data.code == 1) {
                    if (checkImg == 0) {
                        $("#idCardFront").attr("src", "${path}" + data.imgPath);
                        $('#idCardFront').load(function(){
                            $(".fakeloader"+checkImg).fakeLoader({
                                timeToHide:0,
                                bgColor:"#ececec",
                                spinner:"spinner2",
                                width: $(selector).width(),
                                height: $(selector).height(),
                                left: $(selector).position().left,
                                top: $(selector).position().top
                            });
                        });
                    } else {
                        $("#idCardBack").attr("src", "${path}" + data.imgPath);
                        $('#idCardBack').load(function(){
                            $(".fakeloader"+checkImg).fakeLoader({
                                timeToHide:0,
                                bgColor:"#ececec",
                                spinner:"spinner2",
                                width: $(selector).width(),
                                height: $(selector).height(),
                                left: $(selector).position().left,
                                top: $(selector).position().top
                            });
                        });
                    }
                } else {
                    alert(data.msg);
                }

            }
        });
    }
    function submit() {
        var cd = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;//定义身份证匹配算法
        var legalIdCard = $("#idCard").val();//获取前台界面字段值
        if (legalIdCard != null && legalIdCard != "") {//运用匹配方法直接判断
            if (cd.test(legalIdCard) == false) {
                alert("身份证号不合法!");
                return;
            }
        } else {
            alert("身份证号不能为空!");
            return;
        }
        if (nameCheckFun($("#name")) == false) {
            return;
        }
        var fCardUrl = $("#idCardFront").attr("src");
        var bCardUrl = $("#idCardBack").attr("src");
        fCardUrl = fCardUrl.substr(fCardUrl.lastIndexOf('/') + 1);
        bCardUrl = bCardUrl.substr(bCardUrl.lastIndexOf('/') + 1);
        var paraData = {};
        paraData.name = $("#name").val();
        paraData.idCard = legalIdCard;
        paraData.idCardFrontUrl = fCardUrl;
        paraData.idCardBackUrl = bCardUrl;
        paraData.type = 0;
        $.ajax({
            url: "${path}/identityAuth/sumbitAudit.do",
            type: "post",
            data: paraData,
            dataType: "json",
            success: function (data) {
                if (data.isError == false) {
                    window.location.href = "${path}/identityAuth/toWaitIdentityPage.html";
                }
                else {
                    alert(data.message);
                    return false;
                }
            }
        });
    }
    function nameCheckFun(data) {
        if ($(data).val() == "") {
            alert("姓名不能为空");
            return false;
        }
        if (!isCardName($(data).val())) {
            alert("姓名是2-15字的汉字");
            return false;
        }
        return true;
        //检验姓名：姓名是2-15字的汉字
        function isCardName(s) {
            var patrn = /^\s*[\u4e00-\u9fa5]{1,}[\u4e00-\u9fa5.·]{0,15}[\u4e00-\u9fa5]{1,}\s*$/;
            if (!patrn.exec(s)) {
                return false;
            }
            return true;
        }
    }
</script>
</html>