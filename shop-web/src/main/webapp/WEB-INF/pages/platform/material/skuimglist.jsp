<%@ page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>麦链合伙人</title>
    <%@include file="/WEB-INF/pages/common/head.jsp" %>
    <link rel="stylesheet" href="${path}/static/css/material/productimage.css">
    <link rel="stylesheet" href="${path}/static/css/dropload.css">
</head>
<body>

<div class="wrap">
    <header class="xq_header">
        <a href="${path}/materielList/groupInfoB"><img src="${path}/static/images/xq_rt.png" alt=""></a>
        <p>产品图片</p>
    </header>
    <main>
        <c:forEach var="mat" items="${materials}">
            <div class="floor">
                <h1>${mat.title}</h1>
                <div class="sec1" id="imagelist">
                    <c:forEach items="${mat.comSkuMaterialItems}" var="img">
                        <a href="#"><img data="${img.fileName}" /></a>
                    </c:forEach>
                </div>
                <p>${mat.content}</p>
            </div>
        </c:forEach>
        <div id="datePlugin"></div>
    </main>
    <img src="${path}/static/images/material/FAB.png" alt="" onclick="clickShow()">
</div>
<div class="black">
    <div class="back_b"></div>
    <div class="b_t">
        <h1>亲爱的代理，</h1>

        <p>
            线下素材的图片像素比较大，请您留下您的邮箱地址，系统稍后会将线下素材的下载链接发到您的邮箱，请注意查收！
        </p>
        <input type="text" placeholder="请输入邮箱地址" id="email">
        <button onclick="saveEmail()">留下邮箱</button>
        <b class="off" onclick="clickHide()">×</b>
    </div>
</div>
<div class="bigphp">
    <div class="back_b"></div>
    <div class="b_p">
        <img src="" alt="">
    </div>
    <b class="off" onclick="bigphpHide()">×</b>
</div>
<script src="${path}/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${path}/static/js/iscroll.js" ></script>
<script src="${path}/static/js/dropload.min.js"></script>
<script>
    function clickShow(){
        $(".black").show();
    }
    function clickHide(){
        $(".black").hide();
    }
    function bigphpHide(){
        $(".bigphp").hide();
    }
    $(".sec1 img").on("click",function(){
        var imgSrc=$(this).attr("src");
        $(".bigphp").show().find("img").attr("src",imgSrc);
    })
    //   下拉
    $('body').dropload({
        scrollArea : window,
        loadDownFn : function(me){
            var pageData = {};
            pageData.mgId = '${mgId}';
            pageData.currentPage = 1;
            $.ajax({
                type: 'post',
                url: '${path}/materielList/materialInfoB',
                data: pageData,
                dataType: 'json',
                success: function(data){
                    alert(11);
                    return;
                },
                error: function(xhr, type){
                    me.resetload();
                }
            });
        }
    });

    //判断浏览器
    var Browser=new Object();
    Browser.userAgent=window.navigator.userAgent.toLowerCase();
    Browser.ie=/msie/.test(Browser.userAgent);
    Browser.Moz=/gecko/.test(Browser.userAgent);

    function Imagess(url,imgid,callback){
        var val=url;
        var img=new Image();
        if(Browser.ie){
            img.onreadystatechange =function(){
                if(img.readyState=="complete"||img.readyState=="loaded"){
                    callback(img,imgid);
                }
            }
        }else if(Browser.Moz){
            img.onload=function(){
                if(img.complete==true){
                    callback(img,imgid);
                }
            }
        }
        //如果因为网络或图片的原因发生异常，则显示该图片
        img.onerror=function(){img.src="${path}/static/images/admin.png"}
        img.src=val;
    }

    //显示图片
    function checkimg(obj,imgid){
        document.getElementById(imgid).style.cssText="";
        document.getElementById(imgid).src=obj.src;
    }
    window.onload=function(){
        var imageList=$("#imagelist");
        $(".sec1 img").each(function(i,n){
            $(".floor").find("img").eq(i).attr("id","img0"+i);
            $(".floor").find("img").eq(i).attr("src","http://www.86y.org/images/loading.gif");
            $(".floor").find("img").eq(i).css("background","url(http://www.86y.org/images/loading.gif) no-repeat center center");
            Imagess($(".floor").find("img").eq(i).attr("data"),$(".floor").find("img").eq(i).attr("id"),checkimg);
        })
    }
    $(function(){
        var bWidth=$(".sec1").width()/3-5;
        $(".sec1 a").css({
            "display":"inline-block",
            "width":bWidth+'px',
            "height":bWidth+'px'
        })
    })
    function saveEmail() {
        var email = $("#email").val();
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
        if (email == null || email == "") {
            alert("邮箱不能为空");
            return false;
        }
        if (!reg.test(email)) {
            alert("请输入正确的邮箱格式");
            return false;
        }
        $.ajax({
            url: '${basePath}materielApply/addEmail.do',
            type: 'post',
            data: {email: email},
            dataType: 'json',
            success: function (data) {
                if (data.isError == false) {
                    alert("邮箱上传成功，请注意查收邮件");
                    $(".black").hide();
                }
            }
        });
    }
</script>
</body>
</html>