<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Upload page</title>
    <meta name="decorator" content="default">
</head>
<body>
    <div class="container-fluid">
        <form action="/fileUpload/upload" method="post" enctype="multipart/form-data" onsubmit="return submitVaild();">
            <div class="form-group">
                <label for="files">bootstrap</label>
                <input id="files" class="form-control" name="files" type="file" onchange="upload()"/>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="上传" />
            </div>
        </form>

        <hr class="layui-bg-green"/>

        <button id="layerUpload" type="button" class="layui-btn">
            <i class="layui-icon">&#xe67c;</i>上传文件
        </button>
    </div>

    <script type="text/javascript">
        /**
         * 上传文件
         */
        function upload() {
            var fileDom = document.getElementById("files");
            var fileName = fileDom.value;
            console.log(fileName);
            var file = fileDom.files[0];
            console.log(file);
        }

        /**
         * 提交前的校验
         */
        function submitVaild() {
            var fileDom = document.getElementById("files");
            var fileName = fileDom.value;
            if(fileName == '' || fileName == null) {
                layer.alert("请选择上传文件！");
                return false;
            }
            return true;
        }


        // layer
        layui.use('upload', function () {
            var upload = layui.upload;

            // 上传实例
            var uploadInst = upload.render({
                elem : '#layerUpload', // 绑定元素
                url : '/fileUpload/upload2', // 上传接口
                accept : 'file', // 上传文件类型
                multiple : true, // 是否支持多文件
                done : function(res) {
                    if(res.isSuccess) {
                        layer.msg("上传成功，文件名为：" + res.fileName);
                    } else {
                        layer.msg("上传失败！");
                    }
                },
                error : function () {
                    layer.msg("系统繁忙，请稍后再试！");
                }
            });
        });
    </script>
</body>
</html>
