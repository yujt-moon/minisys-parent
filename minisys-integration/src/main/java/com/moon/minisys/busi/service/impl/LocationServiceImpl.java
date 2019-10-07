package com.moon.minisys.busi.service.impl;

import com.moon.minisys.busi.dao.LocationDao;
import com.moon.minisys.busi.pojo.Location;
import com.moon.minisys.busi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yujiangtao
 * @date 2018/7/24 15:22
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDao locationDao;

    public List<Location> getLocations() {
        return locationDao.getLocations();
    }

    public List<Location> getNearbyLocations(Map<String, Double> queryMap) {
        return locationDao.getNearbyLocations(queryMap);
    }

    public List<Location> getRadiusLocations(Map<String, Double> queryMap) {
        return locationDao.getRadiusLocations(queryMap);
    }
}
