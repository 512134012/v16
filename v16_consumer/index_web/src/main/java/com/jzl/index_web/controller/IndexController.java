package com.jzl.index_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jzl.api.IProductService;
import com.jzl.api.IProductTypeService;
import com.jzl.entity.TProduct;
import com.jzl.entity.TProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-08 上午 11:34
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @Reference
    private IProductTypeService iProductTypeService;

    @Reference
    private IProductService iProductService;

    @RequestMapping("home")
    private String showType(Model model){
        List<TProductType> list = iProductTypeService.list();
        List<TProduct> productList = iProductService.list();
        model.addAttribute("plist",productList);
        model.addAttribute("list",list);
        return "index";
    }


}
