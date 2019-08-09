package com.jzl.background_web.file;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.jzl.vo.ResultBean;
import com.jzl.vo.WangEditorResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-06 下午 10:02
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Value("${image.server}")
    private String imageServer;

    /**
     * 单个文件上传
     * @param file
     * @return
     */
    @RequestMapping("upload")
    public ResultBean uploadFile(MultipartFile file){
        //获取文件的后缀名
        //1、上传的文件名
        String originalFilename = file.getOriginalFilename();
        //2、上传文件的后缀名
        String exName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            //3、上传到远程仓库
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage
                    (file.getInputStream(), file.getSize(), exName, null);
            //4、获取存储路径
            String imagePath = new StringBuilder(imageServer).append(storePath.getFullPath()).toString();
            return new ResultBean("200",imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResultBean("500","上传失败");
    }

    @RequestMapping("uploads")
    public WangEditorResultBean uploadFiles(MultipartFile[] files){
        String data[] = new String[files.length];
        //遍历文件files
        for (int i = 0; i < files.length ; i++) {
            //获取文件的后缀名
            //1、上传的文件名
            String originalFilename = files[i].getOriginalFilename();
            //2、上传文件的后缀名
            String exName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            try {
                //3、上传到远程仓库
                StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage
                        (files[i].getInputStream(), files[i].getSize(), exName, null);
                //4、获取存储路径
                String imagePath = new StringBuilder(imageServer).append(storePath.getFullPath()).toString();
                //5、数组存储路径
                data[i] = imagePath;
                System.out.println(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
                return new WangEditorResultBean("-1",null);
            }
        }
        return new WangEditorResultBean("0",data);
    }

}
