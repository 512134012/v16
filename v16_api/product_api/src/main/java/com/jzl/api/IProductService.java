package com.jzl.api;

import com.github.pagehelper.PageInfo;
import com.jzl.base.IBaseService;
import com.jzl.entity.TProduct;
import com.jzl.vo.ProductVo;
import com.jzl.vo.ResultBean;

import java.util.List;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-05 下午 12:10
 */
public interface IProductService extends IBaseService<TProduct> {
    //商品分页展示
    PageInfo<TProduct> page(Integer currentPage,Integer pageSize);

    //商品的添加
    Long save(ProductVo productVo);

    //单个商品的下架
    Integer delById(Long id);

    //多个商品的下架
    Long batchDel(List<Long> ids);

    //单个商品上架
    Integer upById(Long id);
}
