$(function() {
    layui.use('form', function(){
        var form = layui.form;
        form.render();
        //各种基于事件的操作，下面会有进一步介绍
    });

    loadLeavesCategories();

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
        $("#leafCategory").val("");
        renderComponent("select");
    });

    // 点击导出
    $(".js-export").click(function() {
        exportGoods();
    });
});

/**
 * 加载所有叶子目录
 */
function loadLeavesCategories() {
    var cid = $("#cid").val();
    $.ajax({
        url: "/category/allLeaves",
        type: "post",
        datatype: "json",
        success: function (data) {
            var htmlFragment = "";
            for(var i = 0; i < data.length; i++) {
                if(cid != null && cid != "" && cid == data[i].id) {
                    htmlFragment += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>';
                } else {
                    htmlFragment += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                }
            }
            $("#leafCategory").append(htmlFragment);
            renderComponent("select");
        }
    });
}

/**
 * 重新加载form组件
 */
function renderComponent(component) {
    layui.use('form', function(){
        var form = layui.form;
        form.render(component);
    });
}

/**
 * 导出数据
 */
function exportGoods() {
    // 下载不可以使用ajax，可以使用表单
    /*$.ajax({
        url: "/item/export",
        type: "post",
        datatype: "json",
        success: function (data) {
            console.log("下载成功");
        }
    });*/
    window.location.href = "/item/export";
}
