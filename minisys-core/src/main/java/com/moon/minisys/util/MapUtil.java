package com.moon.minisys.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 地图工具类
 * @author yujiangtao
 * @date 2018/7/24 15:57
 */
public class MapUtil {

    /**
     * 地球半径千米
     */
    public static final double EARTH_RADIUS_KILOMETER = 6371;

    /**
     * 计算某个经纬度相对于某个经纬度的偏移量
     * @param distance 与经纬度的距离
     * @param lng 经度
     * @param lat 纬度
     * @return
     */
    public static Map<String, Double> caculateOffset(float distance, float lng, float lat) {
        // 地球半径千米
        double radius = EARTH_RADIUS_KILOMETER;
        // 千米距离
        double dis = distance / 1000;
        double dlng = 2 * Math.asin(Math.sin(dis/(2*radius)) / Math.cos(lat*Math.PI/180));
        // 角度转为弧度
        dlng = dlng * 180 / Math.PI;
        double dlat = dis / radius;
        dlat = dlat * 180 / Math.PI;
        double minlat =lat - dlat;
        double maxlat = lat + dlat;
        double minlng = lng - dlng;
        double maxlng = lng + dlng;
        Map<String, Double> queryMap = new HashMap<String, Double>(4);
        queryMap.put("minlat", minlat);
        queryMap.put("maxlat", maxlat);
        queryMap.put("minlng", minlng);
        queryMap.put("maxlng", maxlng);
        return queryMap;
    }
}
