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
    <link rel="stylesheet" href="<%=path%>/static/css/index.css">
    <link rel="stylesheet" href="<%=path%>/static/css/header.css">
    <link rel="stylesheet" href="<%=path%>/static/css/reset.css">
    <link rel="stylesheet" href="<%=path%>/static/plugins/swipwr/swiper.3.1.7.min.css">
    <script src="<%=path%>/static/js/jquery/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/static/plugins/swipwr/swiper.3.1.7.min.js"></script>
</head>
<body>
<div class="wrap">
    <div class="admin">
        <img src="<%=path%>/static/images/admin.png" alt="">
        <h3>王平—欢迎您登入</h3>
        <span>绑定账号</span>
    </div>
    <div class="banner">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide"><img src="<%=path%>/static/images/shouye_banner.png" alt=""></div>
                <div class="swiper-slide"><img src="<%=path%>/static/images/shouye_banner2.png" alt=""></div>
                <div class="swiper-slide"><img src="<%=path%>/static/images/shouye_banner.png" alt=""></div>
            </div>
            <!-- 如果需要分页器 -->
            <div class="swiper-pagination"></div>
        </div>
    </div>
    <div class="sing">
        <img src="<%=path%>/static/images/shouye_sing.png" alt="">
        <h2>麦链推出全新扶持计划！ ！ ！</h2>
    </div>
    <nav>
        <ul>
            <li>
                <a href="javascript:;">
                    <h1><img src="<%=path%>/static/images/shouye_nav.png" alt=""></h1>
                    <span>男士</span>
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <h1><img src="<%=path%>/static/images/shouye_nav2.png" alt=""></h1>
                    <span>女士</span>
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <h1><img src="<%=path%>/static/images/shouye_nav3.png" alt=""></h1>
                    <span>美食</span>
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <h1><img src="<%=path%>/static/images/shouye_nav4.png" alt=""></h1>
                    <span>美妆</span>
                </a>
            </li>
        </ul>
    </nav>
    <section class="sec1">
        <h1>推荐产品</h1>
        <div class="cp_1">
            <p><img src="<%=path%>/static/images/cp_1.png" alt=""></p>
            <h2 style="width:5px;"></h2>
            <p><img src="<%=path%>/static/images/cp_1_2.png" alt=""></p>
        </div>
        <div class="cp_xq">
            <div>
                <p><a href="<%=path%>/lo/detail">抗引力—快速瘦脸精华</a></p>
                <h2>
                    <span>￥328</span>
                    成为合伙人可查看利润
                </h2>
                <h3>
                    <span></span>
                    超过<b>9999</b>人合伙
                    <a href="<%=path%>/lo/quote">我要合伙</a>
                </h3>
            </div>
            <div>
                <p><a href="<%=path%>/lo/detail">抗引力—快速瘦脸精华</a></p>
                <h2>
                    <span>￥328</span>
                    成为合伙人可查看利润
                </h2>
                <h3>
                    <span></span>
                    超过<b>9999</b>人合伙
                    <a href="<%=path%>/lo/quote">我要合伙</a>
                </h3>
            </div>
        </div>
    </section>
</div>
<footer>
    <div>
        <a href="javascript:;">
            <span><img src="<%=path%>/static/images/shouye_footer.png" alt=""></span>
            <span>首页</span>
        </a>
    </div>
    <div>
        <a href="javascript:;">
            <span><img src="<%=path%>/static/images/shouye_footer2.png" alt=""></span>
            <span>我的店铺</span>
        </a>
    </div>
    <div>
        <a href="javascript:;">
            <span><img src="<%=path%>/static/images/shouye_footer3.png" alt=""></span>
            <span>个人中心</span>
        </a>
    </div>
</footer>
<script>
    var mySwiper = new Swiper ('.swiper-container', {
        direction: 'horizontal',
        loop: true,
        autoplay: 3000,
        autoplayDisableOnInteraction : false,
        // 如果需要分页器
        pagination: '.swiper-pagination'
    })

</script>
</body>
</html>