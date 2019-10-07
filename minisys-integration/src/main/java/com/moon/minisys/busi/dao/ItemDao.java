package com.moon.minisys.busi.dao;

import com.moon.minisys.busi.pojo.Item;

import java.util.List;

/**
 * 商品Dao
 * @author yujiangtao
 * @date 2018/5/9 16:16
 */
public interface ItemDao {

    /**
     * 根据条件获取商品信息
     * @param item
     * @return
     */
    List<Item> getItems(Item item);
}
