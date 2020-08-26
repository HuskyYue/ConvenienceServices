package com.xinyunkeji.bigdata.convenience.server.service.log;

import com.xinyunkeji.bigdata.convenience.model.entity.SysLog;
import com.xinyunkeji.bigdata.convenience.model.mapper.SysLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志监听service
 *
 * @author Yuezejian
 * @date 2020年 08月22日 21:06:22
 */
@Service
public class LogService {

    @Autowired
    private SysLogMapper logMapper;

    //记录日志
    public void recordLog(SysLog log) throws Exception {
        logMapper.insertSelective(log);
    }
}