package com.jzl.search_service;

import com.github.pagehelper.PageInfo;
import com.jzl.api.ISearchService;
import com.jzl.entity.TProduct;
import com.jzl.vo.ResultBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceApplicationTests {

    @Autowired
    private ISearchService iSearchService;

    @Test
    public void contextLoads() {

        PageInfo<TProduct> pageInfo = iSearchService.queryByIdKeyWord(1, 2, "架构");


    }

}
