package com.yezi.office.oss.service.impl;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.yezi.office.oss.service.OssService;
import com.yezi.office.oss.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.oss.service.impl
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 19:09
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvatar(MultipartFile file) {
        // 需要四个参数，注意不要泄露 ACCESS_KEY_ID 和 ACCESS_KEY_SECRET
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        String url = null;

        //构建日期路径：avatar/2019/02/26/文件名
        String filePath = new DateTime().toString("yyyy/MM/dd");
        //文件名：uuid.扩展名
        String original = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString().replaceAll("-","");
        String fileType = original.substring(original.lastIndexOf("."));
        String newName = fileName + fileType;
        String fileUrl = filePath + "/" + newName;

        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            // 上传文件流。
            ossClient.putObject(bucketName, fileUrl, inputStream);
            url = "https://" + bucketName + "." + endpoint + "/" + fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭OSSClient。
            if (ossClient != null){
                ossClient.shutdown();
            }
        }

        return url;
    }
}
