package com.yezi.office.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.oss.service
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 19:09
 */
public interface OssService {
    String uploadAvatar(MultipartFile file);
}
