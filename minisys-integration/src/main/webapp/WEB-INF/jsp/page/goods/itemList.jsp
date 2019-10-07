<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/tag/taglib.jsp"%>
<html>
<head>
    <title>Item List</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="decorator" content="default">
    <script type="text/javascript" src="/static/busi/js/goods/itemList.js"></script>
</head>
<body style="overflow: scroll;">
    <form class="layui-form" action="/item/list" method="post">
    <div class="container-fluid" style="margin-top: 10px;">
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="text" name="title" placeholder="请输入商品名称" class="layui-input" value="${item.title}" />
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">类目</label>
            <div class="layui-input-inline">
                <input id="cid" type="hidden" value="${item.cid}">
                <select id="leafCategory" name="cid">
                    <option value=""></option>
                </select>
            </div>
        </div>
        <div class="layui-inline" style="margin-left: 10px;">
            <button class="layui-btn layui-btn-normal layui-btn-radius js-search">查询</button>
            <button class="layui-btn layui-btn-primary layui-btn-radius js-reset">重置</button>
        </div>

        <div><a class="layui-btn layui-btn-normal layui-btn-radius js-export" style="color: white;">导出</a></div>

        <table class="layui-table" lay-even="">
            <colgroup>
                <col width="100">
                <col width="300">
                <col width="100">
                <col>
                <col width="100">
                <col width="100">
                <col width="100">
            </colgroup>
            <thead>
            <tr>
                <th>商品id</th>
                <th>商品名称</th>
                <th>商品价格（￥）</th>
                <th>商品卖点</th>
                <th>库存数量</th>
                <th>所属类目</th>
                <th>商品状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.list}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.title}</td>
                    <td>${item.price/100.0}</td>
                    <td>${item.sellPoint}</td>
                    <td>${item.num}</td>
                    <td>${item.categoryName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${item.status == 1}">
                                正常
                            </c:when>
                            <c:when test="${item.status == 2}">
                                下架
                            </c:when>
                            <c:when test="${item.status == 1}">
                                删除
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%@ include file="/WEB-INF/jsp/component/page.jsp"%>
    </div>
    </form>
</body>
</html>
