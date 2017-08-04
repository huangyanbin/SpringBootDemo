<#assign ctx = request.contextPath />

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>${article.title}</title>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="${ctx}/css/weui.css"/>
    <link rel="stylesheet" href="${ctx}/css/example.css"/>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script typet="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        var pageSize = 10;
        var commentPage = 1;
        var isGoCommentBottom = false;
        window.onload = function () {
            var imgs = document.getElementsByTagName('img');
            for (var i = 0; i < imgs.length; i++) {
                var imgClazz = imgs[i].getAttribute('class');
                if (imgClazz === null) {
                    imgs[i].setAttribute('class', 'img-responsive');
                }
            }
            getCommentData();
        }
        /**
         * 获取评论数据
         */
        function getCommentData() {
            if (isGoCommentBottom) {
                alert("没有更多评论了");
                return;
            }
            $.getJSON("/comment/list/" + commentPage + "/" + pageSize, {articleId: '${article.id}'}, function (result) {
                if (result.code === 1) {
                    if ($.isArray(result.data)) {
                        for (var i = 0; i < result.data.length; i++) {
                            var comment = result.data[i];
                            $("#commentDiv").append("<div class='col-md-12'>"
                            +"<div style='display: inline;width: 30px;margin-left: 15px;margin-right:15px;margin-top:5px;float: left'>"
                                    +"<img src="+comment.user.icon +" class='img-circle' width='30px' height='30px'/></div>"
                                    +" <div style='margin-left: 15px;display: inline;float: left;'>"
                                    +"<font color='#3CC51F'>"+comment.user.nickName+"</font>"
                                    +"<p  style='color:#999999;'><small >"+comment.user.intro+"</small></p>"
                                    +"<h5 style='color:#333;'>"+comment.content+"</h5>"
                                    +"<p><small style='color:#888;'>"
                                    +timeFormat(comment.commitTime)
                                    +"</small><p> <hr/></div> </div>");
                        }
                        if (result.data.length === 0 && commentPage === 1) {
                            $("#commentDiv").append("<h5 style='color:#333;'>暂无评论</h5>");
                        }
                        if (result.data.length < 5) {
                            isGoCommentBottom = true;
                        } else {
                            commentPage++;
                        }
                    }
                }
            });
        }
        function timeFormat(time){
            var date = new Date(time),
                    curDate = new Date(),
                    year = date.getFullYear(),
                    month = date.getMonth() + 10,
                    day = date.getDate(),
                    hour = date.getHours(),
                    minute = date.getMinutes(),
                    curYear = curDate.getFullYear(),
                    curHour = curDate.getHours(),
                    timeStr;

            if(year < curYear){
                timeStr = year +'年'+ month +'月'+ day +'日 ';
            }else{
                var pastTime = curDate - date,
                        pastH = pastTime/3600000;

                if(pastH > curHour){
                    timeStr = month +'月'+ day +'日 ';
                }else if(pastH >= 1){
                    timeStr = '今天 ' + hour +':'+ minute +'分';
                }else{
                    var pastM = curDate.getMinutes() - minute;
                    if(pastM > 1){
                        timeStr = pastM +'分钟前';
                    }else{
                        timeStr = '刚刚';
                    }
                }
            }
            return timeStr;
        }
    </script>

</head>
<body>
<#include "head_comm.ftl"/>
<div class="page">
    <div class="hd">
        <h1 class="page_title">${article.title}</h1>
    </div>
    <div class="bd">
        <div style="height: 60px;margin:0 auto;">
            <div style="display: inline;width: 50px;margin-left: 15px;margin-top:5px;float: left">
                <img src="${article.user.icon!}" class="img-circle" width="50px" height="50px"/>
            </div>
            <div style="margin-left: 15px;display: inline;float: left;">
                <font color="#333">${article.user.nickName!}</font>
                <div>
                    <small style="color:#555;margin-left: 5px;">${article.user.company!}</small>
                    <small style="color:#888;">${article.user.position!}</small>
                </div>
                <small style="color:#888;">${article.user.intro!}</small>
            </div>
            <div style="display: inline;float: right;margin-right: 10px">
                <p style="color: #3CC51F">分类：${article.type.title!}</p>
            </div>
        </div>
        <article class="weui_article">
            <section>
                <p>${article.content}</p>
            </section>
        </article>
    </div>
    <div class="col-md-6 col-md-offset-3" style="margin-bottom: 15px">
        <div style="display:inline; float:right;color: #3CC51F;font-size: 15px;">
            <span class="glyphicon glyphicon-comment"></span> ${article.commentCount}
        </div>
        <div style="display:inline; float:right;color: #3CC51F;font-size: 15px;margin-right: 8px">
            <span class="glyphicon glyphicon-star-empty"></span> ${article.favCount}
        </div>

        <div style="display:inline; float:right;color: #3CC51F;font-size: 15px;margin-right: 8px">
            <span class="glyphicon glyphicon-heart"></span> ${article.like}
        </div>


    </div>
    <div class="col-md-6 col-md-offset-3">
        <ul class="pager" style="color:#3CC51F">
            <li class="previous"><a href="/artDetail/${pageNum-1}">&larr; 上一页</a></li>
            <li class="next"><a href="/artDetail/${pageNum+1}">下一页 &rarr;</a></li>
        </ul>
    </div>

    <div class="col-md-10 col-md-offset-1" id="commentDiv">
        <h4>评论</h4>
        <hr />
    </div>
</div>
</body>
</html>