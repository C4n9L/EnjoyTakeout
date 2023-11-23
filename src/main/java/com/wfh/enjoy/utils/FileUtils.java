package com.wfh.enjoy.utils;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author wfh
 * @create 2023/3/30 - 14:33
 */
@Slf4j
@Component
public class FileUtils {

    @Value("${enjoy.qiniu.host}")
    private String host;

    @Value("${enjoy.qiniu.ak}")
    private String ak;

    @Value("${enjoy.qiniu.sk}")
    private String sk;

    @Value("${enjoy.qiniu.bucket}")
    private String bucket;

    @Autowired
    private UploadManager uploadManager;

    public String FileUpload(MultipartFile file, String key){

        Auth auth = Auth.create(ak,sk);
        String upToken = auth.uploadToken(bucket);
        String fileUrl = "";
        try {
            Response response = uploadManager.put(file.getBytes(),key,upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            String res = putRet.key;
            fileUrl = host + res;

        } catch (QiniuException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileUrl;
    }
}
