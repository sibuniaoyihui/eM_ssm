package com.liuwei.ssm.service;

import com.liuwei.ssm.domain.Permission;
import com.liuwei.ssm.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll(int page,int pageSize) throws Exception;
    public void save(Role role)throws Exception;
    Role findById(String id)throws Exception;
    List<Permission> findOtherPermissions(String roleId);
    void addPermissionToRole(String roleId,String[]permissionIds)throws Exception;
}
