package com.liuwei.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.liuwei.ssm.dao.PermissionDao;
import com.liuwei.ssm.domain.Permission;
import com.liuwei.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("/permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll(int page,int pageSize) throws Exception {

        PageHelper.startPage(page,pageSize);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }
}
