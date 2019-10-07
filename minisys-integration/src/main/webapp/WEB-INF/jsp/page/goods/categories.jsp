<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/tag/taglib.jsp"%>
<html>
<head>
    <title>Category</title>
    <meta name="decorator" content="black">
    <%@ include file="/WEB-INF/include/layui.jsp"%>
</head>
<body>
    <ul id="root" style="margin-left: 20px;margin-top: 20px;"></ul>

    <script type="text/javascript">
        // 初始化所有商品类别
        function init() {
            var categories = null;
            $.ajax({
                url: "/category/getData",
                data : null,
                type:"post",
                dataType:"json",
                async: false,
                success:function(data){
                    console.log(data);
                    categories = data;
                }
            });
            return categories;
        }

        var options = {
            elem: '#root', //传入元素选择器
            click: function(node) {
                console.log(node);
            },
            nodes: init()
        };

        layui.use('tree', function(){
            layui.tree(options);
        });
    </script>
</body>
</html>
