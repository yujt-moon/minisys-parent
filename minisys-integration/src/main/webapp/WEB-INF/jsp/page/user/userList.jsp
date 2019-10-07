<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/tag/taglib.jsp"%>
<html>
<head>
    <title>User List</title>
    <meta name="decorator" content="default">
    <link rel="stylesheet" href="/static/common/layui/css/modules/layer/default/layer.css"/>
    <script type="text/javascript" src="/static/common/layui/lay/modules/layer.js"></script>
    <script type="text/javascript" src="/static/busi/js/user/userList.js"></script>
</head>
<body>
    <div class="container-fluid table-responsive">
        <div class="mt-2">
            <button class="layui-btn layui-btn-lg js-addNew">
                <i class="layui-icon">&#xe654;</i>
            </button>
        </div>

        <table class="table table-hover w-100 mt-3">
            <thead>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Locked</th>
                <th>Operate</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.list}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>
                        <i class="layui-icon" style="color: <c:if test="${user.locked}">red</c:if><c:if test="${!user.locked}">green</c:if>; font-size: 20px;">&#xe673;</i>
                    </td>
                    <td>
                        <i class="layui-icon" style="font-size: 20px;">&#xe642;</i> |
                        <i class="layui-icon" style="color: #F00;font-size: 20px;">&#xe640;</i>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%@ include file="/WEB-INF/jsp/component/page.jsp"%>
    </div>
</body>
</html>
