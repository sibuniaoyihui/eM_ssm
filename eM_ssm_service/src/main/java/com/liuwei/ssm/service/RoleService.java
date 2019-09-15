package com.liuwei.ssm.service;

import com.liuwei.ssm.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll() throws Exception;
    public void save(Role role)throws Exception;
}
