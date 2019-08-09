package com.jzl.api;

import com.github.pagehelper.PageInfo;
import com.jzl.entity.TProduct;
import com.jzl.vo.ResultBean;

import java.util.List;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-08 下午 5:37
 */
public interface ISearchService {

    //全量添加到索引库，初始化一下
    ResultBean synAllSearch();

    //单个添加索引库
    ResultBean synGetByIdSearch(Long id);

    //按关键字进行搜索
    PageInfo<TProduct> queryByIdKeyWord(Integer currentPage, Integer pageSize, String keywords);


}
