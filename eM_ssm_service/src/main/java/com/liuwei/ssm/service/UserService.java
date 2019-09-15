package com.liuwei.ssm.service;

import com.liuwei.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserInfo> findAll(int page,int pageSize) throws Exception;
    void save(UserInfo user);
    void modifyPsw(String username,String psw);
    UserInfo findById(String id) throws Exception;
}
