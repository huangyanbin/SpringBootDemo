<#assign ctx = request.contextPath />

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script typet="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $(".btn").click(function () {
                var userName = $("#inputPhone").val();
                var pwd = $("#inputPwd").val();
                $.post("/user/login", {userName: userName, password: pwd}, function (result) {
                   if(result.code ===1){
                       window.location="/artList";
                   }else{
                       alert(result.result);
                   }
                }, 'json');
            });
        });
    </script>
</head>
<body>
<form>
    <div class="form-group col-md-4 col-md-offset-3">
        <label for="exampleInputEmail1">手机号码</label>
        <input type="number" class="form-control" id="inputPhone" placeholder="手机号码">
    </div>
    <div class="form-group col-md-4 col-md-offset-3">
        <label for="exampleInputPassword1">密码</label>
        <input type="password" class="form-control" id="inputPwd" placeholder="密码">
    </div>
    <div class="form-group col-md-6 col-md-offset-3" align="center">
    <button class="btn btn-danger">提交</button>
    </div>
</form>


</body>
</html>