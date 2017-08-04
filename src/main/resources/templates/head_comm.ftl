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
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <style type="text/css">
        body{background-color:#FBF9FE}
    </style>
    <script src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <#--<script typet="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>-->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript">
        function addArticle() {
            window.location= "/artAdd";
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

            <img src="${ctx}/img/add_article.png" onclick="addArticle()"
                 style="width: 24px;height: 24px;margin-left: 25px"/>
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

        </div>
    </div>
</nav>

</body>
</html>