package com.moon.minisys.busi.dao;

import com.moon.minisys.busi.pojo.Book;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2018/7/23 23:05
 */
public interface BookDao {

    public List<Book> queryBooks();
}
