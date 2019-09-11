package com.liuwei.ssm.dao;

import com.liuwei.ssm.domain.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    @Select("select *from role where id in (select roleId from users_role where userId = #{userId})")
    public List<Role>findById(String userId)throws Exception;

    @Insert("insert into users_role values(#{userId},'333')")
    public void save(String userId);
}
