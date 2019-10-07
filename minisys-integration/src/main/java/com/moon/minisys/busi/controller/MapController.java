package com.moon.minisys.busi.controller;

import com.moon.minisys.busi.pojo.Location;
import com.moon.minisys.busi.service.LocationService;
import com.moon.minisys.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地图相关功能
 * @author yujiangtao
 * @date 2018/7/24 15:12
 */
@Controller
@RequestMapping("/map")
public class MapController {

    @Autowired
    private LocationService locationService;

    /**
     * 跳转高德地图页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/toPage")
    public String toPage(HttpServletRequest request, Model model) {
        return "integration/map";
    }

    /**
     * 获取所有的位置信息
     * @return
     */
    @RequestMapping("/getData")
    @ResponseBody
    public List<Location> getLocationData() {
        List<Location> locations = locationService.getLocations();
        return locations;
    }

    /**
     * 获取某个方形区域的所有数据
     * @return
     */
    @RequestMapping("/getNearby")
    @ResponseBody
    public List<Location> getNearbyLocations() {
        Map<String, Double> queryMap = MapUtil.caculateOffset(100, 118.75144f, 31.97349f);
        List<Location> nearbyLocations = locationService.getNearbyLocations(queryMap);
        return nearbyLocations;
    }

    /**
     * 获取圆形区域的位置信息（？？有问题）
     * @return
     */
    @RequestMapping("/getRadius")
    @ResponseBody
    public List<Location> getRadiusLocations() {
        Map<String, Double> queryMap = new HashMap<String, Double>();
        queryMap.put("lng", 118.75144);
        queryMap.put("lat", 31.97349);
        queryMap.put("distance", 100d);
        List<Location> radiusLocations = locationService.getRadiusLocations(queryMap);
        return radiusLocations;
    }

    @RequestMapping("/toH5Map")
    public String toH5API() {
        return "integration/h5map";
    }
}
