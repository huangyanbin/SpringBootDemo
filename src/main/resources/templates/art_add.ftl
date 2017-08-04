<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>simditor</title>
    <link rel="stylesheet" type="text/css" href="styles/simditor.css" />
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="scripts/module.min.js"></script>
    <script type="text/javascript" src="scripts/hotkeys.min.js"></script>
    <script type="text/javascript" src="scripts/uploader.min.js"></script>
    <script type="text/javascript" src="scripts/simditor.min.js"></script>
</head>

<body>
<nav class="navbar navbar-default" role="navigation" style="background-color: white">
    <div class="container-fluid col-md-offset-1">
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
    </div>

<form class="form-horizontal" role="form" style="margin-top: 30px">
    <div class="form-group">
        <label for="firstname" class="col-sm-2 control-label">文章名</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="articleTitle" placeholder="请输入文章名">
        </div>
    </div>
    <div class="form-group">
        <label for="lastname" class="col-sm-2 control-label">文章地址</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="articleUrl" placeholder="请输入URL">
        </div>
    </div>
    <label for="" class="col-sm-2 control-label">文章内容</label>
    <div class="form-group col-sm-8 col-sm-offset-2">
        <textarea id="editor"  placeholder="请输入文章内容" autofocus></textarea>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-8" align="center">
            <button  class="btn btn-default">提交</button>
        </div>
    </div>
</form>

<script>
    var editor = new Simditor({
        textarea: $('#editor')
        //optional options
    });
    $(document).ready(function () {
        $(".btn").click(function () {
                var title = $("#articleTitle").val();
                var content = $("#editor").val();
                var url = $("#articleUrl").val();
                $.post("/article/add", {
                    title: title,
                    url: url,
                    content: content,
                    uid: 1,
                    typeId: 2
                }, function (result) {
                    if (result.code === 1) {
                        alert("添加文章成功");
                    } else {
                        alert(result.result);
                    }
                },'json');
        });
    });

</script>

</body>
</html>