package com.liuwei.ssm.service;

import com.liuwei.ssm.domain.SysLog;

import java.util.List;

public interface SysLogService {

    public void save(SysLog sysLog) throws Exception;

    public List<SysLog> findAll(Integer page,Integer pageSize) throws Exception;
}
