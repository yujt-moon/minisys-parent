package com.moon.minisys.busi.controller;

import com.alibaba.fastjson.JSONArray;
import com.moon.minisys.common.RedisKey;
import com.moon.minisys.jedis.JedisClient;
import com.moon.minisys.busi.pojo.ItemCat;
import com.moon.minisys.busi.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 类别控制器
 * @author yujiangtao
 * @date 2018/7/5 17:48
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ItemCatService itemCatService;

    @Autowired
    private JedisClient jedisClient;

    @RequestMapping("/list")
    public String showCategories() {
        return "page/goods/categories";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public String getCategoryData() {
        String treeStr = "";
        if(jedisClient.get(RedisKey.ITEM_CATEGORY_TREE) == null) {
            JSONArray itemCats = itemCatService.getItemCats(0L);
            treeStr = itemCats.toJSONString();
            jedisClient.set(RedisKey.ITEM_CATEGORY_TREE, treeStr);

        } else {
            treeStr = jedisClient.get(RedisKey.ITEM_CATEGORY_TREE);
        }
        return treeStr;
    }

    /**
     * 获取叶子节点的分类
     * @return
     */
    @RequestMapping("/allLeaves")
    @ResponseBody
    public List<ItemCat> getAllChildrenItemCats() {
        return itemCatService.getAllChildrenItemCats();
    }
}
