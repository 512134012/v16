package com.jzl.vo;

import com.jzl.entity.TProduct;

import java.io.Serializable;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-06 上午 10:31
 */
public class ProductVo implements Serializable {

    private TProduct tProduct;//商品的基本信息

    private String productDesc;//商品的描述信息

    public ProductVo(TProduct tProduct, String productDesc) {
        this.tProduct = tProduct;
        this.productDesc = productDesc;
    }

    public ProductVo() {
    }

    public TProduct gettProduct() {
        return tProduct;
    }

    public void settProduct(TProduct tProduct) {
        this.tProduct = tProduct;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
