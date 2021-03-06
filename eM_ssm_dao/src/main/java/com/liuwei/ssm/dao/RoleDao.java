package com.liuwei.ssm.dao;

import com.liuwei.ssm.domain.Permission;
import com.liuwei.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface RoleDao {

    @Select("select *from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select ="com.liuwei.ssm.dao.PermissionDao.findById"))
    })
    public List<Role>findById(String userId)throws Exception;

    @Select("select *from role where id = #{roleId}")
    public Role findBySoloId(String roleId);

    @Insert("insert into users_role values(#{userId},'333')")
    public void save(String userId);


    @Select("select *from role")
    public List<Role> findAll()throws Exception;

    @Insert("insert into role(roleName,roleDesc)values(#{roleName},#{roleDesc})")
    public void saveNewRole(Role role);

    @Select("select *from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findOtherPermissions(String roleId);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
