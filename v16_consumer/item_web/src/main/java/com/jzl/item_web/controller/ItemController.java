package com.jzl.item_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jzl.api.IProductService;
import com.jzl.entity.TProduct;
import com.jzl.vo.ResultBean;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-09 下午 5:45
 */
@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private Configuration configuration;//freemarker配置接口

    @Autowired
    private ThreadPoolExecutor pool;

    @Reference
    private IProductService iProductService;

    /**
     * 生成静态页面调用封装好的静态页面方法
     * @param id
     * @return
     */
    @RequestMapping("createHTML/{id}")
    public ResultBean createHTML(@PathVariable("id") Long id){
        return createHTMLTask(id);
    }

    /**
     * 单个生成静态页面的方法（封装调用）
     * @param id
     * @return
     */
    public ResultBean createHTMLTask(@PathVariable("id") Long id){
        //1、获得id对象信息
        TProduct tProduct = iProductService.selectByPrimaryKey(id);

        //2、获得页面模板
        try {
            Template template = configuration.getTemplate("item.ftl");
            //3、设置模板的数据
            Map<String,Object> map = new HashMap<>();
            map.put("product",tProduct);
            //4、生成静态页面
            //获得路径static
            String serverport = ResourceUtils.getURL("classpath:static").getPath();
            //构建输出流对象
            FileWriter fileWriter = new FileWriter(serverport+ File.separator+id+".html");
            //生成静态页面
            template.process(map,fileWriter);

        } catch (IOException |TemplateException e) {
            e.printStackTrace();
            return new ResultBean("500","静态页面生成失败");
        }
        return new ResultBean("200","静态页面生成成功");
    }


    @RequestMapping("batchcreateHTML")
    public ResultBean batchcreateHTML(@RequestParam List<Long>ids){
        
        return null;
    }




}
