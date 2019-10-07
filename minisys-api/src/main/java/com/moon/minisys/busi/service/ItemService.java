package com.moon.minisys.busi.service;

import com.github.pagehelper.PageInfo;
import com.moon.minisys.busi.pojo.Item;
import com.moon.minisys.pojo.Limit;

import java.util.List;

/**
 * 商品Service
 * @author yujiangtao
 * @date 2018/5/9 16:25
 */
public interface ItemService {

    /**
     * 根据条件获取商品信息
     * @param item
     * @return
     */
    List<Item> getItems(Item item);

    /**
     * 根据条件获取商品信息
     * @param item
     * @param limit
     * @return
     */
    PageInfo<Item> getItemsByPage(Item item, Limit limit);
}
