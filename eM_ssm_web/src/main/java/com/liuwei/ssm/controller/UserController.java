package com.liuwei.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.liuwei.ssm.domain.Role;
import com.liuwei.ssm.domain.UserInfo;
import com.liuwei.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    //查询指定id的用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id)throws Exception{

        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "pageSize",required = true,defaultValue = "5")Integer pageSize)throws Exception{
        List<UserInfo> users = userService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(users);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }
    //用户添加
    @RequestMapping("/save.do")
    public String save(UserInfo user)throws Exception{

        userService.save(user);
        return"redirect:findAll.do";

    }

    //修改密码
    @RequestMapping("/modifyPsw.do")
    public String modifyPsw(@RequestParam(name = "password")String password){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.modifyPsw(user.getUsername(),password);
        return "redirect:/logout.do";
    }

    //查询用户，以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception {
        //根据用户id查询用户
        UserInfo userInfo = userService.findById(userId);
        //根据用户id查询可以添加的角色
        List<Role> roles = userService.findOtherRoles(userId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }
   //给用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds){

        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

}
