package com.moon.minisys.busi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moon.minisys.busi.dao.ItemDao;
import com.moon.minisys.busi.pojo.Item;
import com.moon.minisys.pojo.Limit;
import com.moon.minisys.busi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品 ServiceImpl
 * @author yujiangtao
 * @date 2018/5/9 16:28
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    /**
     * 查询所有商品信息
     * @param item
     * @return
     */
    public List<Item> getItems(Item item) {
        return itemDao.getItems(item);
    }

    /**
     * 分页查询商品信息
     * @param item
     * @param limit
     * @return
     */
    public PageInfo<Item> getItemsByPage(Item item, Limit limit) {
        PageHelper.startPage(limit.getPageNum(), limit.getPageSize());
        List<Item> items = itemDao.getItems(item);
        PageInfo<Item> pageInfo = new PageInfo<Item>(items);
        return pageInfo;
    }
}
