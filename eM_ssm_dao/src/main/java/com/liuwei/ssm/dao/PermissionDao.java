package com.liuwei.ssm.dao;

import com.liuwei.ssm.domain.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    //根据id查询权限值

    @Select("select *from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findById(String roleId)throws Exception;

    @Select("select *from permission")
    public List<Permission> findAll()throws Exception;
}
