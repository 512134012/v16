package com.jzl.product_service.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jzl.api.IProductTypeService;
import com.jzl.base.BaseServiceImpl;
import com.jzl.base.IBaseDao;
import com.jzl.entity.TProductType;
import com.jzl.mapper.TProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-06 下午 3:52
 */
@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<TProductType> implements IProductTypeService {

    @Autowired
    private TProductTypeMapper tProductTypeMapper;


    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return tProductTypeMapper;
    }
}
