<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/tag/taglib.jsp"%>
<html>
<head>
    <%@ include file="/WEB-INF/include/commonHead.jsp" %>
    <%@ include file="/WEB-INF/include/bootstrap.jsp" %>
    <%@ include file="/WEB-INF/include/layui.jsp" %>
    <title><sitemesh:title/></title>
    <sitemesh:head/>
</head>
<body>
<%--<!-- 面包屑 -->
<ol class="breadcrumb ${themeColor}" style="margin-bottom: 0.5rem;">
    <li class="breadcrumb-item active"><a href="javascript:void(0);">Home</a></li>
</ol>--%>
<div>
    <div class="mini-menu ${themeColor} menu-font" style="height: 100%;">
        <nav class="nav flex-column">
            <a class="nav-link active js-toggleNavShow" href="javascript:void(0);">
                UserCenter
                <i class="layui-icon" style="float: right">&#xe625;</i>
            </a>
            <nav class="nav nav-pills flex-column sub-menu hide" style="display: none;">
                <a class="nav-link ml-3 my-1" href="/user/list">
                    <i class="layui-icon">&#xe770;</i> Users
                </a>
                <a class="nav-link ml-3 my-1" href="/login/logout">
                    <i class="layui-icon">&#xe69c;</i> Logout
                </a>
                <a class="nav-link ml-3 my-1" href="#item-1-2">Item 1-2</a>
            </nav>
            <a class="nav-link active js-toggleNavShow" href="javascript:void(0);">
                ItemCenter
                <i class="layui-icon" style="float: right">&#xe625;</i>
            </a>
            <nav class="nav nav-pills flex-column sub-menu hide" style="display: none;">
                <a class="nav-link ml-3 my-1" href="/item/list">
                    <i class="layui-icon">&#xe678;</i> Items
                </a>
                <a class="nav-link ml-3 my-1" href="/category/list">
                    <i class="layui-icon">&#xe62e;</i> Category (Layer Tree)
                </a>
            </nav>
            <a class="nav-link active js-toggleNavShow" href="javascript:void(0);">
                FunctionMenu
                <i class="layui-icon" style="float: right">&#xe625;</i>
            </a>
            <nav class="nav nav-pills flex-column sub-menu hide" style="display: none;">
                <a class="nav-link ml-3 my-1" href="/test/freemarker">Ftl Test</a>
                <a class="nav-link ml-3 my-1" href="/fileUpload/toUploadPage">
                    <i class="layui-icon" style="color: #01AAED;">&#xe681;</i> File Upload
                </a>
            </nav>
            <a class="nav-link active js-toggleNavShow" href="javascript:void(0);">
                Map
                <i class="layui-icon" style="float: right">&#xe625;</i>
            </a>
            <nav class="nav nav-pills flex-column sub-menu hide" style="display: none;">
                <a class="nav-link ml-3 my-1" href="/map/toPage">
                    <i class="layui-icon">&#xe770;</i> 高德地图API
                </a>
                <a class="nav-link ml-3 my-1" href="/map/toH5Map">
                    <i class="layui-icon">&#xe69c;</i> H5原生API
                </a>
            </nav>
        </nav>
    </div>
    <div style="overflow: hidden;">
        <sitemesh:body/>
    </div>
</div>
</body>
</html>
