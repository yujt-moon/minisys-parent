package com.moon.minisys.busi.service;

import com.alibaba.fastjson.JSONArray;
import com.moon.minisys.busi.pojo.ItemCat;

import java.util.List;


/**
 * @author yujiangtao
 * @date 2018/7/5 20:15
 */
public interface ItemCatService {

    JSONArray getItemCats(Long parentId);

    List<ItemCat> getAllChildrenItemCats();
}
