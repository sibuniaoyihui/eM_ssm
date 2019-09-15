package com.liuwei.ssm.service;

import com.liuwei.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll()throws Exception;
}
