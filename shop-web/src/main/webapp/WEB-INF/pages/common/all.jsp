<%@ page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title></title>
    <link rel="stylesheet" href="${path}/static/css/reset.css">
    <style>
        body,html{
            width: 100%;
            height: 100%;
            display: -webkit-box;
            -webkit-box-orient:vertical;
        }
        body>img{
            width: 100%;
            display: block;
        }
        main{
            display: -webkit-box;
            -webkit-box-orient:vertical;
            -webkit-box-align: center;
            -webkit-box-flex:1;
            background: url("${path}/static/images/backg.png") no-repeat;
            background-size: 100% 100%;
        }
        .logo{
            width: 70px;
            height: 70px;
            display: block;
            margin:0 auto;
            margin-top: 30%;
        }
        h1{
            text-align: center;
            font-size: 14px;
            color: #333;
            position: relative;
            margin-top: 5%;
        }
        h1:after{
            content: "" ;
            width: 60px;
            border-bottom: 2px solid #999;
            position: absolute;
            left: 5px;
            bottom: -18px;
        }
        h2{
            width: 100%;
            text-align: center;
            color:#666;
            margin:15% 0 10% 0;
        }
        button{
            display: block;
            width: 40%;
            padding: 8px 0;
            border:1px solid #E7A22B;
            font-size: 16px;
            color: #333;
            background: #F6F6F6;
            border-radius: 3px;
        }
        p{
            text-align: center;
            color:#666;
            width: 100%;
        }
    </style>
</head>
<body>
<img src="${path}/static/images/headerimg.png" alt="" id="himg" style="display:none">
<main>
    <img src="${path}/static/images/ic_launcher(5).png" alt="" class="logo">
    <h1>麦链合伙人</h1>
    <h2>下载麦链合伙人APP，加入好的创业团队，轻松赚钱。</h2>
    <button id="download">
        下载
    </button>
    <p>支持IOS/Aandroid</p>
</main>
</body>
<script>
//    var hImg=document.getElementById("himg")
//    is_weixn();
//    function is_weixn(){
//        var ua = navigator.userAgent.toLowerCase();
//        if(ua.match(/MicroMessenger/i)=="micromessenger") {
//            return hImg.style="display:block";
//        }else{
//           return hImg.style="display:none";
//        }
//    }
/*
 * 智能机浏览器版本信息:
 *
 */
var browser = {
    versions: function() {
        var u = navigator.userAgent;
        var app = navigator.appVersion;
        return {//移动终端浏览器版本信息
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
            iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
            isQQ : u.indexOf('MQQBrowser') > -1 && u.toLowerCase().match(/micromessenger/i)!="micromessenger",
            isWeixin : u.toLowerCase().match(/micromessenger/i)=="micromessenger"
        };
    }(),
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
}

function is_weixin() {
    var hImg = document.getElementById("himg")
    if(browser.versions.isWeixin) {
//                hImg.setAttribute("style", "display:none;");
        hImg.removeAttribute('style');
    }
}
is_weixin();


//    document.writeln("语言版本: " + browser.language+"<br/>");
//    document.writeln(" 是否为移动终端: " + browser.versions.mobile+"<br/>");
//    document.writeln(" ios终端: " + browser.versions.ios+"<br/>");
//    document.writeln(" android终端: " + browser.versions.android+"<br/>");
//    document.writeln(" 是否为iPhone: " + browser.versions.iPhone+"<br/>");
//    document.writeln(" 是否iPad: " + browser.versions.iPad+"<br/>");
//    document.writeln("isQQ : "+browser.versions.isQQ+"<br/>");
//    document.writeln("u.indexOf('MQQBrowser) : "+ navigator.userAgent.indexOf('MQQBrowser')+"<br/>" );
//    document.writeln("isWeixin : "+browser.versions.isWeixin+"<br/>");
//    document.writeln("<br/>")
//    document.writeln(navigator.userAgent+"<br/>");

document.getElementById("download").onclick = function() {

    if(!browser.versions.mobile || browser.versions.isWeixin ) {
        alert("请用手机浏览器打开!");
        return;
    }

    if (browser.versions.ios || browser.versions.iPhone || browser.versions.iPad) {
        window.location = 'https://itunes.apple.com/us/app/mai-lian-he-huo-ren/id1112109557?mt=8';
    } else if (browser.versions.android) {
        window.location = "http://file.masiis.com/app/shop-agent-final_Aligned.apk";
    }

}
</script>
<%--<script src="<%=path%>/static/js/download.js"></script>--%>
</html>