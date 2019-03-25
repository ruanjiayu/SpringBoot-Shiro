package com.fan.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ruanjiayu
 * @dateTime 2019/3/21 11:06
 */
@Controller
@Slf4j
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        log.info("进入登录页面");
        //表示定位到login页面下
        return "login";
    }

    @RequestMapping("/login")
    public String login(String name,String password,Model model){
        log.info("开始进行校验");

        /**
         * 使用shiro编写认证操作
         */
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        //3.执行登录方法
        try{
            subject.login(token);
            //登录成功 来进行重定向
            return "redirect:user/testThymeleaf";
        }catch(UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码不正确");
            return "login";
        }
    }

    @RequestMapping("/unAuth")
    public String unAuth(){
        return "unAuth";
    }

}
