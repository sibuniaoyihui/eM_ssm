package com.liuwei.ssm.controller;


import com.liuwei.ssm.domain.SysLog;
import com.liuwei.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;
    private Class clazz;//访问的类
    private Method method;//访问的方法
    private String url;
    private String ip;


    //前置通知
    @Before("execution(* com.liuwei.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime = new Date();//当前时间就是访问时间
        clazz = joinPoint.getTarget().getClass();//获取当前访问的类
        String methodName = joinPoint.getSignature().getName();//获取当前访问方法的名字
        Object args[] = joinPoint.getArgs();//获取当前访问方法的参数

        //获取执行放的method对象
        if (args == null || args.length == 0){
            method = clazz.getMethod(methodName);//获取无参的方法
        }else {
            //
            Class [] classes = new Class[args.length];
            for (int i = 0; i < args.length ; i++) {
                classes[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,classes);//获取有参的方法
        }
    }

    //后置通知
    @After("execution(* com.liuwei.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        Long time = new Date().getTime() - visitTime.getTime();//获取访问的时长

        //获取URL
        if (method != null && clazz != null && clazz != LogAop.class){
         //获取类上的@RequestMapping中的路径值
            RequestMapping classAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null){
                String[] classValue = classAnnotation.value();

                //获取方法上的value值
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String [] methodValue = methodAnnotation.value();

                    url = classValue[0] + methodValue[0];

                    //获取访问的ip地址
                    ip = request.getRemoteAddr();

                    //获取当前操作对象
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    User user = (User)securityContext.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志相关信息封装到SysLog中
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);//执行时间
                    sysLog.setUsername(username);//操作者
                    sysLog.setVisitTime(visitTime);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setMethod("[类名] "+clazz.getName()+" [方法名] "+method.getName());

                    //调用Service完成插入操作
                    sysLogService.save(sysLog);
                }
            }
        }


    }
}
