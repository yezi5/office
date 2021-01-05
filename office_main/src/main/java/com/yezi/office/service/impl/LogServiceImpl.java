package com.yezi.office.service.impl;

import com.yezi.office.pojo.Log;
import com.yezi.office.mapper.LogMapper;
import com.yezi.office.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志记录表 服务实现类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
