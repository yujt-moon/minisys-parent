<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<input type="hidden" name="pageNum" id="clickedNum" value="${page.pageNum}" />
<input type="hidden" name="pageSize" id="clickedSize" value="${page.pageSize}" />

<div class="layui-box layui-laypage layui-laypage-default float-right" id="layui-laypage">
    <span class="layui-laypage-count">共 ${page.total} 条</span>
    <a href="javascript:;" class="layui-laypage-prev <c:if test="${page.pageNum == 1}">layui-disabled</c:if>" data-page="0">上一页</a>

    <!-- 页数小于等于7直接全部展示 -->
    <c:if test="${page.pages <= 7}">
        <c:forEach begin="1" end="${page.pages}" step="1" var="var">
            <c:choose>
                <c:when test="${var == page.pageNum}">
                    <span class="layui-laypage-curr"><em class="layui-laypage-em"></em><em>${var}</em></span>
                </c:when>
                <c:otherwise>
                    <a href="javascript:;" data-page="${var}">${var}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:if>

    <%--页数大于7分三种情况展示--%>
    <c:if test="${page.pages > 7}">
        <c:choose>
            <%-- 当前页小于等于4 --%>
            <c:when test="${page.pageNum <= 4}">
                <c:forEach begin="1" end="5" step="1" var="var">
                    <c:choose>
                        <c:when test="${var == page.pageNum}">
                            <span class="layui-laypage-curr"><em class="layui-laypage-em"></em><em>${var}</em></span>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:;" data-page="${var}">${var}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <span class="layui-laypage-spr">…</span>
                <a href="javascript:;" class="layui-laypage-last" title="尾页" data-page="${page.pages}">${page.pages}</a>
            </c:when>

            <%-- 当前页大于等于5并且总页数减去当前页数大于三 --%>
            <c:when test="${page.pageNum > 4 && page.pages - page.pageNum > 3}">
                <a href="javascript:;" data-page="1">1</a>
                <span class="layui-laypage-spr">…</span>
                <a href="javascript:;" data-page="${page.pageNum-2}">${page.pageNum-2}</a>
                <a href="javascript:;" data-page="${page.pageNum-1}">${page.pageNum-1}</a>
                <span class="layui-laypage-curr"><em class="layui-laypage-em"></em><em>${page.pageNum}</em></span>
                <a href="javascript:;" data-page="${page.pageNum+1}">${page.pageNum+1}</a>
                <a href="javascript:;" data-page="${page.pageNum+2}">${page.pageNum+2}</a>
                <span class="layui-laypage-spr">…</span>
                <a href="javascript:;" class="layui-laypage-last" title="尾页" data-page="${page.pages}">${page.pages}</a>
            </c:when>
            <%-- 当前页大于等于5并且总页数减去当前页数小于三 --%>
            <c:when test="${page.pageNum > 4 && page.pages - page.pageNum <= 3 && page.pages - page.pageNum >= 0}">
                <a href="javascript:;" data-page="1">1</a>
                <span class="layui-laypage-spr">…</span>
                <c:if test="${page.pages - page.pageNum == 0}">
                    <c:forEach begin="0" step="1" end="4" var="var">
                        <a href="javascript:;" data-page="${page.pages-(5-var)}">${page.pages-(5-var)}</a>
                    </c:forEach>
                    <span class="layui-laypage-curr"><em class="layui-laypage-em"></em><em>${page.pageNum}</em></span>
                </c:if>

                <c:if test="${page.pages - page.pageNum != 0}">
                    <c:forEach begin="0" step="1" end="4" var="var">
                        <c:choose>
                            <c:when test="${page.pages - page.pageNum == 4- var}">
                                <span class="layui-laypage-curr"><em class="layui-laypage-em"></em><em>${page.pages-(4-var)}</em></span>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:;" data-page="${page.pages-(4-var)}">${page.pages-(4-var)}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
            </c:when>
        </c:choose>
    </c:if>

    <a href="javascript:;" class="layui-laypage-next <c:if test="${page.pageNum == page.pages}">layui-disabled</c:if>" data-page="2">下一页</a>
    <span class="layui-laypage-limits">
        <select id="selecedSize" lay-ignore="">
            <c:forEach begin="10" step="10" end="50" var="var">
                <option value="${var}" <c:if test="${var == page.pageSize}">selected</c:if>>
                    ${var} 条/页
                </option>
            </c:forEach>
        </select>
    </span>
    <a href="javascript:;" data-page="1" class="layui-laypage-refresh">
        <i class="layui-icon layui-icon-refresh"></i>
    </a>
    <span class="layui-laypage-skip">到第<input id="pageInput" type="text" min="1" value="${page.pageNum}" class="layui-input">页<button type="button" class="layui-laypage-btn">确定</button>
    </span>
</div>

<script type="text/javascript">
    // 点击分页组件
    $(".layui-laypage a:not('.layui-laypage-prev'):not('.layui-laypage-next')").click(function() {
        // 获取当前点击的页码
        var pageNum = $(this).attr("data-page");
        console.log(pageNum);
        // 每页记录的条数
        var pageSize = $("#selecedSize").val();
        $("#clickedNum").val(pageNum);
        $("#clickedSize").val(pageSize);
        document.forms[0].submit();
    });

    // 点击每页下拉
    $("#selecedSize").change(function() {
        // 获取当前每页记录数
        var pageSize = $("#selecedSize").val();
        var pageNum = 1;
        $("#clickedNum").val(pageNum);
        $("#clickedSize").val(pageSize);
        document.forms[0].submit();
    });

    // 页码输入框
    $("#pageInput").keyup(function() {
        var value = $(this).val();
        var reg = /^\d+\d$/;
        if(!reg.test(value)) {
            value = value.substring(0, value.length-1);
            console.log("value: " + value);
            $(this).val(value);
        }
    });

    // 点击确定，跳转对应的页码
    $(".layui-laypage-btn").click(function() {
        var jumpNo = $("#pageInput").val();
        if(jumpNo > ${page.pages}) {
            jumpNo = ${page.pages};
        }
        // 获取当前每页记录数
        var pageSize = $("#selecedSize").val();
        $("#clickedNum").val(jumpNo);
        $("#clickedSize").val(pageSize);
        document.forms[0].submit();
    });

    // 点击上一页进行翻页
    $(".layui-laypage a.layui-laypage-prev:not('.layui-disabled')").click(function() {
        var curPageNo = $("#clickedNum").val();
        var prePageNo = parseInt(curPageNo) - 1;
        // 每页记录的条数
        var pageSize = $("#selecedSize").val();
        $("#clickedNum").val(prePageNo);
        $("#clickedSize").val(pageSize);
        document.forms[0].submit();
    });

    // 点击下一页进行翻页
    $(".layui-laypage a.layui-laypage-next:not('.layui-disabled')").click(function() {
        var curPageNo = $("#clickedNum").val();
        var nextPageNo = parseInt(curPageNo) + 1;
        // 每页记录的条数
        var pageSize = $("#selecedSize").val();
        $("#clickedNum").val(nextPageNo);
        $("#clickedSize").val(pageSize);
        document.forms[0].submit();
    });
</script>
