<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Map</title>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.6&key=c95334644608438bb283448ead8f7850"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    <!-- 为解决http chrome和ios无法获取定位的问题 -->
    <script type="text/javascript" src="http://a.amap.com/jsapi_demos/static/remogeo/remogeo.js"></script>
</head>
<body>
<div id="container" tabindex="0"></div>
<script type="text/javascript">
    var map = new AMap.Map('container',{
        resizeEnable: true,
        // 显示级别（3-18）3表示国家级别
        zoom: 16,
        //center: [116.480983, 40.0958]
    });

    for(var key in AMap.UA) {
        //alert(key + " : " +AMap.UA[key]);
    }

    /*if (AMap.UA.ios) {

        //使用远程定位，见 remogeo.js
        var remoGeo = new RemoGeoLocation();

        //替换方法
        navigator.geolocation.getCurrentPosition = function() {
            return remoGeo.getCurrentPosition.apply(remoGeo, arguments);
        };

        //替换方法
        navigator.geolocation.watchPosition = function() {
            return remoGeo.watchPosition.apply(remoGeo, arguments);
        };
    }*/

    getCurrentLocation();

    // 获取当前的定位
    function getCurrentLocation() {
        map.plugin('AMap.Geolocation', function () {
            geolocation = new AMap.Geolocation({
                enableHighAccuracy: true,//是否使用高精度定位，默认:true
                timeout: 10000,          //超过10秒后停止定位，默认：无穷大
                maximumAge: 0,           //定位结果缓存0毫秒，默认：0
                convert: true,           //自动偏移坐标，偏移后的坐标为高德坐标，默认：true
                showButton: true,        //显示定位按钮，默认：true
                buttonPosition: 'LB',    //定位按钮停靠位置，默认：'LB'，左下角
                buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                showMarker: true,        //定位成功后在定位到的位置显示点标记，默认：true
                showCircle: true,        //定位成功后用圆圈表示定位精度范围，默认：true
                panToLocation: true,     //定位成功后将定位到的位置作为地图中心点，默认：true
                zoomToAccuracy:true      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
            });
            map.addControl(geolocation);
            geolocation.getCurrentPosition();
            AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
            AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
        });
    }

    /**
     * 定位成后的回调函数
     */
    function onComplete(GeolocationResult) {
        var lngLat = GeolocationResult.position;
        var lng = lngLat.getLng();
        var lat = lngLat.getLat();
        console.log("经度为： " + lng + "纬度为： " + lat);
        alert("经度为： " + lng + "纬度为： " + lat);
    }

    function onError(data) {
        alert("locate failed!");
        for(var key in data) {
            alert(key + ":" + data[key]);
        }
    }

    //为地图注册click事件获取鼠标点击出的经纬度坐标
    var clickEventListener = map.on('click', function(e) {
        console.log(e.lnglat.getLng() + ',' + e.lnglat.getLat());
    });

    /**
     * 获取表中所有位置信息
     */
    function getLocations() {
        var locations = null;
        $.ajax({
            url: "/map/getData",
            data : null,
            type:"post",
            dataType:"json",
            async: false,
            success:function(data){
                //console.log(data);
                locations = data;
            }
        });
        return locations;
    }

    /**
     * 计算两点间的距离，并输出在控制台
     */
    function caculateDistance(lng1, lat1, lng2, lat2, locationName) {
        var lnglat1 = new AMap.LngLat(lng1, lat1);
        var lnglat2 = new AMap.LngLat(lng2, lat2);
        var distance = Math.round(lnglat1.distance(lnglat2));
        return distance;
    }

    /**
     * 将所有的地址信息展示在控制台中
     */
    function showAllLocationsOnConsole() {
        var locations = getLocations();
        for(var i = 0; i < locations.length; i++) {
            var location = locations[i];
            var distance = caculateDistance(location.lng, location.lat, 118.75144, 31.97349, location.locationName);
            console.log(location.locationName + "与中心点相距：" + distance + "米");
        }
        console.log("--------------------------------------------------------");
    }

    showAllLocationsOnConsole();

    /**
     * 获取方形区域的所有位置
     * @returns {*}
     */
    function getNearbyLocations() {
        var locations = null;
        $.ajax({
            url: "/map/getNearby",
            data : null,
            type:"post",
            dataType:"json",
            async: false,
            success:function(data){
                //console.log(data);
                locations = data;
            }
        });
        return locations;
    }

    /**
     * 将方形区域中的点打印在控制台
     */
    function showAllNearbyOnConsole() {
        var locations = getNearbyLocations();
        for(var i = 0; i < locations.length; i++) {
            var location = locations[i];
            console.log(location.locationName);
        }
        console.log("--------------------------------------------------------");
    }

    showAllNearbyOnConsole();

    /**
     * 获取精准的圆形区域中的位置
     */
    function getAccuracyLocations() {
        var curLng = 118.75144;
        var curLat = 31.97349;
        var locations = getNearbyLocations();
        for(var i = 0; i < locations.length; i++) {
            var location = locations[i];
            var distance = caculateDistance(location.lng, location.lat, curLng, curLat, location.locationName);
            if(distance <= 100) {
                console.log(distance + ":" + location.locationName);
            }
        }
        console.log("--------------------------------------------------------");
    }

    getAccuracyLocations();

    /**
     * sql查询的圆形区域（？？）有问题
     * @returns {*}
     */
    function getRadius() {
        var locations = null;
        $.ajax({
            url: "/map/getRadius",
            data : null,
            type:"post",
            dataType:"json",
            async: false,
            success:function(data){
                locations = data;
            }
        });
        return locations;
    }

    function showRadiusLoactions() {
        var locations = getRadius();
        for(var i = 0; i < locations.length; i++) {
            var location = locations[i];
            console.log("sql" + location.locationName);
        }
        console.log("--------------------------------------------------------");
    }
    showRadiusLoactions();
</script>
</body>
</html>
