package com.yezi.office.oss.controller;

import com.yezi.office.oss.service.OssService;
import com.yezi.office.oss.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNullApi;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.oss.controller
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 19:17
 */
@RestController
@RequestMapping("/office/oss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService service;

    @PostMapping(value = "/uploadAvatar")
    public R uploadOssFile(MultipartFile file){

        String url = service.uploadAvatar(file);

        return R.ok().data("url",url);
    }

}
