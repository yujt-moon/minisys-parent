package service;

import com.alibaba.fastjson.JSONArray;
import com.moon.minisys.busi.service.ItemCatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring集成测试
 * @author yujiangtao
 * @date 2019/5/27 17:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext*.xml")
public class ItemCateTest {

    @Autowired
    private ItemCatService itemCatService;

    @Test
    public void testGetItemCats() {
        JSONArray itemCats = itemCatService.getItemCats(0L);
        System.out.println(itemCats.toJSONString());
    }
}
