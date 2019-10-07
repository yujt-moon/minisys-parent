<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="/WEB-INF/include/commonHead.jsp" %>
    <%@ include file="/WEB-INF/include/bootstrap.jsp" %>
</head>
<body style="background-image:url(/static/imgs/login_bg.jpg); background-position: 50%">
    <div class="container">
        <form action="/login/login" method="post">
            <div class="form-group w-50 m-auto" style="margin-top: 13rem !important;">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" name="username" aria-describedby="usernameHelp" placeholder="Enter username">
                <small id="usernameHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group w-50 m-auto mt-sm-2">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
            </div>
            <div class="form-check w-50 m-auto mt-sm-2">
                <label class="form-check-label">
                    <input type="checkbox" class="form-check-input">
                    Remember Me
                </label>
            </div>
            <button type="submit" class="btn btn-primary offset-sm-5 mt-sm-2">Submit</button>
        </form>
    </div>
    <script type="text/javascript">
        // 支持直接回车登录
        window.onload = function() {
            window.addEventListener('keydown', function(event) {
                // 回车键
                if(event.keyCode == 13) {
                    document.forms[0].submit();
                }
            });
        };
    </script>
</body>
</html>
