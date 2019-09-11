package com.liuwei.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.liuwei.ssm.dao.RoleDao;
import com.liuwei.ssm.dao.UserDao;
import com.liuwei.ssm.domain.Role;
import com.liuwei.ssm.domain.UserInfo;
import com.liuwei.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
           userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //自己的用户对象，封装成UserDetails
//        User user = new User(userInfo.getUsername(),userInfo.getPassword(),
//                          userInfo.getStatus() == 0 ? false : true,true,true,true,getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),
                userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }
    //返回一个List集合，集合中加入角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList();
        for (Role role : roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll(int page,int pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo user) {

        //对密码进行加密处理
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        //赋予用户默认的Role值USER
        System.out.println(user.getId());
        roleDao.save(user.getId());

    }

    @Override
    public void modifyPsw(String username,String password) {
       //加密密码
       password = bCryptPasswordEncoder.encode(password);
       userDao.modifyPsw(username,password);
    }
}
