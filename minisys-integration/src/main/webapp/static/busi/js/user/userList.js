$(function() {
    // 点击查询按钮
    $(".js-search").click(function() {
        // 从第一页开始
        $("#clickedNum").val(1);
        // 提交表单
        document.forms[0].submit();
    });

    // 点击重置按钮
    $(".js-reset").click(function(e) {
        e.preventDefault();
        document.forms[0].title.value = "";
    });

    // 点击新增
    $(".js-addNew").click(function (e) {
        e.preventDefault();
        // 打开弹窗
        layer.open({
            type: 2,
            shade: false,
            area: '500px',
            maxmin: true
        });
    });
});