package com.moon.minisys.busi.controller;

import com.github.pagehelper.PageInfo;
import com.moon.minisys.busi.pojo.Item;
import com.moon.minisys.pojo.Limit;
import com.moon.minisys.busi.service.ItemService;
import com.moon.minisys.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品 Controller
 * @author yujiangtao
 * @date 2018/5/9 16:31
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 展示商品列表
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String showItemList(Item item, Limit limit, HttpServletRequest request, Model model) {
        PageInfo<Item> page = itemService.getItemsByPage(item, limit);
        model.addAttribute("page", page);
        model.addAttribute("item", item);
        return "page/goods/itemList";
    }

    @RequestMapping("/export")
    public void exportItems(Item item, HttpServletResponse response) throws Exception {
        List<Item> items = itemService.getItems(item);
        ExcelUtils.exportCVS(response, "item.csv", items);
        response.getWriter().write("haha");
    }
}
