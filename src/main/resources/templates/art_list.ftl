<#assign ctx = request.contextPath />

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>文章列表</title>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <style type="text/css">
        body{background-color:#FBF9FE}
    </style>
    <script src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script typet="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script src="${ctx}/js/tab-1.0.js"></script>
    <script type="text/javascript">
        var page = 1;//全局变量
        var pageSize = 5;
        var isGoBottom = false;
        $(document).ready(function () {
            getArticleData();
            $("#articleBtn").click(function () {
                if(isGoBottom){
                    alert("已经是最后一页了");
                    return;
                }
                getArticleData();
            });
        });




        /** 获取文章列表数据
         * */

        function getArticleData() {

            $.getJSON("/article/list/" + page + "/"+pageSize, {uid: 1}, function (result) {
                if (result.code === 1) {
                    if ($.isArray(result.data)) {
                        fillTabClass($("#ArtTable"),"文章列表",[
                            {   name:"ID",
                                key:"id"
                            },
                            {   name:"图标",
                                key:"icon",
                                type:"img",
                                style:"width:40px;height:40px;",class:"img-circle"},
                            {   name:"主题",
                                key:"title",
                                type:"span"
                            },
                            {name:"时间",
                                key:"createTime",
                                format:function (time) {
                                           return timeFormat(time);
                                        }
                            }
                            ],result.data,"table-bordered");
                        for (var i = 0; i < result.data.length; i++) {
                            var article = result.data[i];
                            fillHtmlElement2($("#articleDiv"),$("#articleCustom"),article,
                                    [
                                        {   key:"createTime",
                                            format:function(time) {
                                                return timeFormat(time);
                                            }
                                        }
                                    ]
                            );
                        }
                        if (page === 1) {
                            $(".articleItem").click(function () {
                                var index = $(this).index()-1;
                                window.location = "/artDetail/"+index;
                            });
                        }
                        if (result.data.length < pageSize) {
                            isGoBottom = true;
                        } else {
                            page++;
                        }

                    }

                }
            });
        }

        function addArticle() {
            window.location= "/artAdd";
        }

        function timeFormat(time){
            var date = new Date(time),
                    curDate = new Date(),
                    year = date.getFullYear(),
                    month = date.getMonth() + 1,
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
<nav class="navbar navbar-default" role="navigation" style="background-color: white">
    <div class="container-fluid col-md-offset-2 col-md-8">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">掘金</a>
        </div>
        <div>
            <ul class="nav navbar-nav" >
                <li class="active"><a href="#">首页</a></li>
                <li><a href="#">专栏</a></li>
                <li><a href="#">收藏栏</a></li>
                <li><a href="#">发现</a></li>
            </ul>
        </div>
        <div style="float: right;display: inline;position: relative; margin-top: 10px" align="center">

            <img src="${Session.User.icon!"${ctx}/img/add_article.png"}" class="img-circle" style="width: 32px;height: 32px;"/>
            <span class="dropdown-toggle" data-toggle="dropdown" style="margin-right: 10px;margin-left: 5px">${Session.User.nickName!"朋友"}</span>
            <ul class="dropdown-menu">
                <li><a href="#">信息</a></li>
                <li><a href="#">关注的人</a></li>
                <li><a href="#">关注自己的人</a></li>
                <li class="divider"></li>
                <li><a href="#">喜欢</a></li>
                <li class="divider"></li>
                <li><a href="#">收藏</a></li>
            </ul>

            <img src="${ctx}/img/add_article.png" onclick="addArticle()"
                 style="width: 24px;height: 24px;margin-left: 25px"/>
        </div>
    </div>
</nav>


<div class="col-md-8 col-md-offset-2" style="margin-top: 20px; background-color:white;">
    <table id="ArtTable" class="table table-responsive">
    </table>
</div>
    <div class="col-md-8 col-md-offset-2" id="articleDiv"style="margin-top: 20px; background-color:white;">
        <h4>精选</h4>
        <hr />
    </div>
<div class="col-md-6 col-md-offset-3">
    <ul class="pager" style="color:#3CC51F">
        <li class="next" id="articleBtn">下一页 &rarr;</li>
    </ul>
</div>
<!--自动拼接数据-->
<huang:custom id="articleCustom" style='display:none'>
    <div class='col-md-12 articleItem'>
        <div style='display: inline;width: 50px;margin-right:15px;margin-top:5px;float: left'>
            <img src=$icon$ width='50px' height='50px'/></div>
        <div class='col-md-10' style='margin-left: 10px;display: inline;float: left;'>
            <p  style='color:#333; overflow:hidden;text-overflow:ellipsis;white-space:nowrap;'>$title$</p>
            <p  style='color:#999999; overflow:hidden;  text-overflow:ellipsis;white-space:nowrap;'>
                <small >$content$</small></p>
            <div class='col-md-11'>
                <div style='display: inline;width: 30px;margin-right:15px;margin-top:2px;float: left'>
                    <img src=$user.icon$ class='img-circle' width='25px' height='25px'/></div>
                <small style='color:#333;margin-top: 5px;'>$user.nickName$</small></div>
            <p><small style='color:#888;'>$createTime$</small><p> <hr/></div> </span>
    </div>
</huang:custom>

</body>
</html>