package com.jzl.mapper;

import com.jzl.base.IBaseDao;
import com.jzl.entity.TProduct;


import java.util.List;

public interface TProductMapper extends IBaseDao<TProduct> {

    Long batchDel(List<Long> ids);

}