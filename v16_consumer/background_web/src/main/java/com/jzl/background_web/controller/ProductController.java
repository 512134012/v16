package com.jzl.background_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jzl.api.IProductService;
import com.jzl.api.IProductTypeService;
import com.jzl.api.ISearchService;
import com.jzl.entity.TProduct;
import com.jzl.entity.TProductType;
import com.jzl.vo.ProductVo;
import com.jzl.vo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-05 下午 5:44
 */
@Controller
@RequestMapping("back")
public class ProductController {

    @Reference
    private IProductService iProductService;

    @Reference
    private ISearchService iSearchService;

    @Reference
    private IProductTypeService iProductTypeService;


    /**
     * 商品分页管理展示
     * @param model
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("product/{currentPage}/{pageSize}")
    public String pageShow(Model model,
                           @PathVariable("currentPage") Integer currentPage,
                           @PathVariable("pageSize") Integer pageSize){
        //获取分页数据
        PageInfo<TProduct> page = iProductService.page(currentPage, pageSize);
        //存入model中带到前端
        model.addAttribute("page",page);
        //跳转到后台
        return "background/product";
    }

    /**
     * 商品添加操作
     * @param productVo
     * @return
     */
    @RequestMapping("save")
    public String save(ProductVo productVo){
        //获取id是为了后续搜索功能使用
        Long id = iProductService.save(productVo);

        //同步添加进搜索索引库
        iSearchService.synGetByIdSearch(id);

        return "redirect:/back/product/1/5";
    }

    /**
     * 商品类别信息
     * @return
     */
    @RequestMapping("typelist")
    @ResponseBody
    public List<TProductType>list(){
        return iProductTypeService.list();
    }

    /**
     * 下架单个商品
     * @param id
     * @return
     */
    @RequestMapping("del/{id}")
    @ResponseBody
    public ResultBean delById(@PathVariable("id") Long id){
        Integer count = iProductService.delById(id);
        if (count<1){
            return new ResultBean("500","下架失败");
        }
        return new ResultBean("200","下架成功");
    }

    /**
     * 下架多个商品
     * @param ids
     * @return
     */
    @RequestMapping("batchDel")
    @ResponseBody
    public ResultBean batchDel(@RequestParam List<Long> ids){
        //调用批量删除的方法
        Long count = iProductService.batchDel(ids);
        //判断是否删除成功
        if (count<1){
            return new ResultBean("500","下架失败");
        }
        return new ResultBean("200","批量下架成功");
    }

    /**
     * 上架多个商品
     * @param id
     * @return
     */
    @RequestMapping("up/{id}")
    @ResponseBody
    public ResultBean upById(@PathVariable("id") Long id){
        Integer count = iProductService.upById(id);
        if (count < 1){
            return new ResultBean("500","上架失败");
        }
        return new ResultBean("200","上架成功");
    }
}
