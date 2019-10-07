package com.moon.minisys.busi.pojo;

/**
 * 地理位置
 * @author yujiangtao
 * @date 2018/7/24 15:13
 */
public class Location {

    private int id;

    /**
     * 地理位置的经度
     */
    private float lng;

    /**
     * 地理位置的纬度
     */
    private float lat;

    /**
     * 地理位置的名称
     */
    private String locationName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
