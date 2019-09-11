package com.liuwei.ssm.dao;

import com.liuwei.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    //根据用户名查找用户
    @Select("select *from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = ("com.liuwei.ssm.dao.RoleDao.findById")))

    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select *from users")
    public List<UserInfo> findAll()throws Exception;


    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    @Options(useGeneratedKeys=true, keyProperty="id",keyColumn = "id")//添加该行
    public void save(UserInfo user);


    //修改密码，两个相同类型时，用@Param注明
    @Update("update users set password =#{password} where username =#{username}")
    public void modifyPsw(@Param("username") String username,@Param("password") String password);
}
