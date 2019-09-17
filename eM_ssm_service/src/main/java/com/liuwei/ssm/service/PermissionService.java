package com.liuwei.ssm.service;

import com.liuwei.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll(int page,int pageSize)throws Exception;

    void save(Permission permission)throws Exception;
}
