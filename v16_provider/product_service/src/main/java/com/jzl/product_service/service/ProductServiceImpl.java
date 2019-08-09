package com.jzl.product_service.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jzl.api.IProductService;
import com.jzl.base.BaseServiceImpl;
import com.jzl.base.IBaseDao;
import com.jzl.entity.TProduct;
import com.jzl.entity.TProductDesc;
import com.jzl.mapper.TProductDescMapper;
import com.jzl.mapper.TProductMapper;
import com.jzl.vo.ProductVo;
import com.jzl.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-05 下午 1:56
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<TProduct> implements IProductService {

    @Autowired
    private TProductMapper tProductMapper;

    @Autowired
    private TProductDescMapper tProductDescMapper;

    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return tProductMapper;
    }

    /**
     * 商品分页展示
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<TProduct> page(Integer currentPage, Integer pageSize) {
        //启动分页
        PageHelper.startPage(currentPage,pageSize);
        //获取分页数据
        List<TProduct> list = list();
        //将分页数据存入pageInfo，并赋予导航页
        PageInfo<TProduct> pageInfo = new PageInfo<>(list,5);
        return pageInfo;
    }

    /**
     * 添加商品
     * @param productVo
     * @return
     */
    @Override
    @Transactional//控制事务
    public Long save(ProductVo productVo) {
        //保存商品的基本信息,设置默认flag为true
        TProduct tProduct = productVo.gettProduct();
        tProduct.setFlag(true);
        //进行主键回填
        int count = tProductMapper.insert(tProduct);
        //保存商品描述的信息
        String productDesc = productVo.getProductDesc();
        TProductDesc tProductDesc = new TProductDesc();
        tProductDesc.setProductDesc(productDesc);
        //回填后在将发id添加到desc表中
        tProductDesc.setProductId(tProduct.getId());
        tProductDescMapper.insert(tProductDesc);
        //返回商品id
        return tProduct.getId();
    }

    /**
     * 删除商品，伪删除，将flag改为false即可，视为下架商品
     * @param id
     * @return
     */
    @Override
    public Integer delById(Long id) {
        TProduct tProduct = new TProduct();
        tProduct.setFlag(false);
        tProduct.setId(id);
        return tProductMapper.updateByPrimaryKeySelective(tProduct);
    }

    /**
     *
     * 下架多个商品
     * @param ids
     * @return
     */
    @Override
    public Long batchDel(List<Long> ids) {
        Long count = tProductMapper.batchDel(ids);
        return count;
    }

    /**
     * 单个商品上架
     * @param id
     * @return
     */
    @Override
    public Integer upById(Long id) {
        TProduct tProduct = new TProduct();
        tProduct.setFlag(true);
        tProduct.setId(id);
        return tProductMapper.updateByPrimaryKeySelective(tProduct);
    }


}