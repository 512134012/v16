package com.jzl.product_service;

import com.github.pagehelper.PageInfo;
import com.jzl.api.IProductService;
import com.jzl.api.IProductTypeService;
import com.jzl.entity.TProduct;
import com.jzl.entity.TProductType;
import com.jzl.vo.ProductVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceApplicationTests {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IProductTypeService iProductTypeService;

    @Test
    public void contextLoads() {

        List<TProductType> list = iProductTypeService.list();
        for (TProductType tProductType : list) {
            System.out.println(tProductType.getName());
        }


    }

    @Test
    public void save(){

        TProduct tProduct = new TProduct();
        tProduct.setName("诸葛亮");
        tProduct.setPrice(88L);
        tProduct.setSalePrice(66L);
        tProduct.setSalePoint("sdf");
        tProduct.setImages("ffsdfdfdsd");
        tProduct.setTypeName("程序员");

        ProductVo productVo = new ProductVo();
        productVo.setProductDesc("哦我我我我我");
        productVo.settProduct(tProduct);

        iProductService.save(productVo);

        System.out.println("添加成功");
    }
}
