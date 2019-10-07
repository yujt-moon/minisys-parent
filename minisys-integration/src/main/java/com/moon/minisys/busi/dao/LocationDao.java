package com.moon.minisys.busi.dao;

import com.moon.minisys.busi.pojo.Location;

import java.util.List;
import java.util.Map;

/**
 * @author yujiangtao
 * @date 2018/7/24 15:16
 */
public interface LocationDao {

    /**
     * 获取所有位置信息
     * @return
     */
    List<Location> getLocations();

    /**
     * 获取某个方形区域中的数据
     * @param queryMap
     * @return
     */
    List<Location> getNearbyLocations(Map<String, Double> queryMap);

    /**
     * 获取圆形区域的位置信息（？？有问题）
     * @param queryMap
     * @return
     */
    List<Location> getRadiusLocations(Map<String, Double> queryMap);
}
