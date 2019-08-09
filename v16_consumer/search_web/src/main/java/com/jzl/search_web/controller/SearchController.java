package com.jzl.search_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jzl.api.ISearchService;
import com.jzl.entity.TProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-08 下午 6:48
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Reference
    private ISearchService iSearchService;

    /**
     * 搜索关键字获取信息
     * @param currentPage
     * @param pageSize
     * @param keywords
     * @param model
     * @return
     */
    @RequestMapping("query/{currentPage}/{pageSize}")
    public String searchpage(@PathVariable Integer currentPage,@PathVariable Integer pageSize, String keywords, Model model){
        //搜索关键字信息
        PageInfo<TProduct> page = iSearchService.queryByIdKeyWord(currentPage, pageSize, keywords);
        model.addAttribute("result",page);
        return "searchpage";
    }



}
