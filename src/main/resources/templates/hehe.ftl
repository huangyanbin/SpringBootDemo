<#assign base = request.contextPath />

<!DOCTYPE html>

<html lang="zh-CN">

<head>

    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="author" content="fyunli">



    <base id="base" href="${base}">

    <title>Spring Boot - hello</title>






</head>



<body>



<!-- Begin page content -->

<div class="container">

    <div class="page-header">

        <h1>Sprint Boot: Hello</h1>

    </div>



    <div>

        Date: ${time?date}

        <br>
        Student ${student.name!'huang'}
        <br>

        Time: ${time?time}

        <br>

        Message: ${message}

    </div>

</div>



<footer class="footer">

    <div class="container">

        <p class="text-muted">&copy;2016 fyunli</p>

    </div>

</footer>



</body>

</html>