<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>H5Map</title>
</head>
<body>
    <div id="demo"></div>

    <h1>
        <a href="tel:13851668086">拨打电话</a>
    </h1>

    <h1>
        <a href="bdapp://map/newsassistant?src=andr.baidu.openAPIdemo">调起出行早晚报页面</a>
    </h1>

    <h1>
        <a href="bdapp://map/direction?region=nanjing&destination=31.974169,118.751663&src=andr.asiainfo.omo">到这去</a>
    </h1>

    <input type="radio" value="hehe" checked>hehe</input>

    <script type="text/javascript">
        var x=document.getElementById("demo");

        getLocation();

        getBrowerType();

        function getLocation(){
            if (navigator.geolocation){
                alert("before");
                navigator.geolocation.getCurrentPosition(showPosition, showError);
                alert("after");
            } else {
                x.innerHTML="Geolocation is not supported by this browser.";
            }
        }

        function showPosition(position) {
            alert("locate success!");
            x.innerHTML="Latitude: " + position.coords.latitude +
                "<br />Longitude: " + position.coords.longitude;
        }

        function showError(error) {
            switch(error.code) {
                case error.PERMISSION_DENIED:
                    alert("定位失败,用户拒绝请求地理定位");
                    break;
                case error.POSITION_UNAVAILABLE:
                    alert("定位失败,位置信息是不可用");
                    break;
                case error.TIMEOUT:
                    alert("定位失败,请求获取用户位置超时");
                    break;
                case error.UNKNOWN_ERROR:
                    alert("定位失败,定位系统失效");
                    break;
            }
        }

        /**
         * 获取浏览器类型
         */
        function getBrowerType() {
            var userAgent = navigator.userAgent;
            alert(userAgent);
        }
    </script>
</body>
</html>
