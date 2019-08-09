package com.jzl.search_service.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jzl.api.ISearchService;
import com.jzl.entity.TProduct;
import com.jzl.mapper.TProductMapper;
import com.jzl.vo.ResultBean;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-08 下午 5:50
 */
@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private TProductMapper tProductMapper;

    @Autowired
    private SolrClient solrClient;

    /**
     * 全量添加到索引库中，只需一次,测试同步就OK了
     * @return
     */
    @Override
    public ResultBean synAllSearch() {
        //获取商品集合
        List<TProduct> list = tProductMapper.list();
        //遍历集合
        for (TProduct tProduct : list) {
            //将对象存入solr文档中
            SolrInputDocument solrInputFields = new SolrInputDocument();
            solrInputFields.setField("id",tProduct.getId());
            solrInputFields.setField("product_name",tProduct.getName());
            solrInputFields.setField("product_price",tProduct.getSalePrice());
            solrInputFields.setField("product_images",tProduct.getImages());
            solrInputFields.setField("product_sale_point",tProduct.getSalePoint());

            //solrclient数据同步
            try {
                solrClient.add(solrInputFields);
            } catch (SolrServerException | IOException e){
                e.printStackTrace();
                return new ResultBean<>("500","数据同步失败");
            }
        }
        //提交数据
        try {
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean<>("500","数据提交失败");
        }
        return new ResultBean<>("500","数据导入成功");

    }


    /**
     * 单个添加到索引库
     * @param id
     * @return
     */
    @Override
    public ResultBean synGetByIdSearch(Long id) {
        //获取当前ID对象
        TProduct tProduct = tProductMapper.selectByPrimaryKey(id);
        //将对象存入solr文档中
        SolrInputDocument solrInputField = new SolrInputDocument();
        solrInputField.setField("id",tProduct.getId());
        solrInputField.setField("product_name",tProduct.getName());
        solrInputField.setField("product_price",tProduct.getSalePrice());
        solrInputField.setField("product_images",tProduct.getImages());
        solrInputField.setField("product_sale_point",tProduct.getSalePoint());

        //solrclient添加同步数据
        try {
            solrClient.add(solrInputField);
            //提交数据
            solrClient.commit();
        } catch (SolrServerException |IOException e) {
            e.printStackTrace();
            return new ResultBean("500","数据同步失败");
        }

        return new ResultBean("200","数据同步成功");
    }

    /**
     * 按关键字查询（分页）
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<TProduct> queryByIdKeyWord(Integer currentPage, Integer pageSize,String keywords) {
        //构建查询条件
        SolrQuery solrQuery = new SolrQuery();
        if (StringUtils.isEmpty(keywords)) {
            solrQuery.setQuery("*:*");
        }else {
            solrQuery.setQuery("product_keywords:"+keywords);
        }
        PageHelper.startPage(currentPage,pageSize);
        //获取查询集合数据
        try {
            QueryResponse response =solrClient.query(solrQuery);
            //获取到查询结果集
            SolrDocumentList documentList = response.getResults();
            //遍历
            List<TProduct>list = new ArrayList<>(documentList.size());
            for (SolrDocument document : documentList) {
                TProduct product = new TProduct();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                product.setName(document.getFieldValue("product_name").toString());
                product.setImages(document.getFieldValue("product_images").toString());
                product.setSalePrice(Long.parseLong(document.getFieldValue("product_price").toString()));
                product.setSalePoint(document.getFieldValue("product_sale_point").toString());

                list.add(product);
            }
            PageInfo pageInfo = new PageInfo(list,5);
            return pageInfo;
        } catch (SolrServerException |IOException e) {
            e.printStackTrace();
            return null;
        }


    }


}
