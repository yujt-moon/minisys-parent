package com.moon.minisys.busi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moon.minisys.busi.dao.ItemCatDao;
import com.moon.minisys.busi.pojo.ItemCat;
import com.moon.minisys.busi.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类别 ServiceImpl
 * @author yujiangtao
 * @date 2018/7/5 20:16
 */
@Service("itemCatService")
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatDao itemCatDao;

    /**
     * 获取所有的商品类别
     * 格式：
     * @param parentId
     * @return
     */
    @Override
    public JSONArray getItemCats(Long parentId) {
        return recuGetItemCat(parentId);
    }

    @Override
    public List<ItemCat> getAllChildrenItemCats() {
        return itemCatDao.getAllChildrenItemCats();
    }

    /**
     * 递归获取商品类别
     * @param parentId
     * @return
     */
    private JSONArray recuGetItemCat(Long parentId) {
        List<ItemCat> itemCats = itemCatDao.getItemCatsByParentId(parentId);
        JSONArray array = new JSONArray();
        for(int i = 0; i < itemCats.size(); i++) {
            ItemCat itemCat = itemCats.get(i);
            JSONObject object = new JSONObject();
            object.put("name", itemCat.getName());
            object.put("id", itemCat.getId());
            // 如果该节点是父节点
            if(itemCat.isParent()) {
                // 递归获取子节点
                JSONArray arr = recuGetItemCat(itemCat.getId());
                // 子节点放到父节点的children中
                object.put("children", arr);
            }
            array.add(object);
        }
        return array;
    }


}
