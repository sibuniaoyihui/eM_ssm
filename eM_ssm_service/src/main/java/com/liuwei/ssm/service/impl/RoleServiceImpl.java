package com.liuwei.ssm.service.impl;

import com.liuwei.ssm.dao.RoleDao;
import com.liuwei.ssm.domain.Role;
import com.liuwei.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception{
        roleDao.saveNewRole(role);
    }
}
