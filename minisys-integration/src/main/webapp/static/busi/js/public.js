/**
 * Created by yujiangtao on 2018/2/28.
 */
$(function() {
    $(document).on("click", ".js-toggleNavShow", function(event) {
        // 阻止事件冒泡
        event.stopPropagation();
        // 切换隐藏或显示
        if($(this).next().hasClass("hide")) {
            $(this).next("nav").removeClass("hide");
            $(this).next("nav").css("display", "flex");
            $(this).find(".layui-icon").html("&#xe623;");
        } else {
            $(this).next("nav").addClass("hide");
            $(this).next("nav").css("display", "none")
            $(this).find(".layui-icon").html("&#xe625;");
        }
    });
});

