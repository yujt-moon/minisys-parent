package com.moon.minisys.busi.service;

import com.moon.minisys.busi.pojo.Location;

import java.util.List;
import java.util.Map;

/**
 * @author yujiangtao
 * @date 2018/7/24 15:21
 */
public interface LocationService {
    List<Location> getLocations();

    List<Location> getNearbyLocations(Map<String, Double> queryMap);

    List<Location> getRadiusLocations(Map<String, Double> queryMap);
}
