package com.moon.minisys.busi.dao;

import com.moon.minisys.busi.pojo.ItemCat;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2018/7/5 20:12
 */
public interface ItemCatDao {

    /**
     * 根据父id查询所有的子节点
     * @param parentId
     * @return
     */
    List<ItemCat> getItemCatsByParentId(Long parentId);

    /**
     * 获取叶子节点的分类
     * @return
     */
    List<ItemCat> getAllChildrenItemCats();
}
