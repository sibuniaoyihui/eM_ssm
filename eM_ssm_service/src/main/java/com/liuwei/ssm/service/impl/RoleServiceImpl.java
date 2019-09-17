package com.liuwei.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.liuwei.ssm.dao.RoleDao;
import com.liuwei.ssm.domain.Permission;
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
    public List<Role> findAll(int page,int pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception{
        roleDao.saveNewRole(role);
    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findBySoloId(id);
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId) {
        return roleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId:permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
