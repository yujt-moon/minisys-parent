<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="decorator" content="default">
    </head>
    <body>
        <div style="margin-left: 20px;">
            <h1>
            <#if msg??>${msg}</#if>
            </h1>
            <div><#if users??>This is a list</#if></div>
            <div>
                <#if user.username == "张三">
                    Hello，${user.username}!
                <#else>
                    Hello, Somebody!
                </#if>
            </div>

            <div>
                <!-- one expression can't avoid empty list displaying ul tag -->
                <ul>
                    <#list users as user>
                        <li>
                            ${user.username} | ${user.password} | ${user?index} | ${user?counter} | ${user?item_parity}
                        </li>
                    </#list>
                </ul>

                <!-- This may be well -->
                <#list users>
                    <ul>
                        <#items as user>
                            <li>
                                ${user.username} | ${user.password}
                            </li>
                        </#items>
                    </ul>
                </#list>
            </div>

            <div>
                Names: <#list users as user>${user.username}<#sep>,</#sep></#list>
            </div>
            <div>
                Names: <#list emptyList as list>${list.username}<#sep>, <#else>None</#list>
            </div>

            <span>
                ${user.username?upper_case} | ${user.username?cap_first} | ${user.username?length} | ${users?size}
            </span>

            <!-- userx don't exist! -->
            <p>${userx!"This is an empty list!"}</p>
            <p><#if !userx??>empty</#if></p>

            <!-- Escaping for HTML, XML and other markup -->
            <#--<#ftl output_format="HTML">${"<div>Direct tag</div>"}</#ftl>-->

            <#assign x = "1.234">
            <span>${x?number?string.percent}</span>

            <#include "copyright_footer.ftl" />
        </div>
    </body>
</html>