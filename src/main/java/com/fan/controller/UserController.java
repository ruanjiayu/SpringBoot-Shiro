package com.fan.controller;

import com.fan.domain.Users;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




/**
 * @author ruanjiayu
 * @dateTime 2019/3/20 13:40
 */
@Controller
@RequestMapping("/user")
/**为了使用这个注解，你需要在pom文件里面添加lombok依赖*/
@Slf4j
public class UserController {


    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
//      为了使用log.()。你需要安装插件lombok
        log.info("输出了hello");
        log.debug("nihao");
        return "ok";
    }

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        /**
         * 可以获取到目前登录的用户是谁
         */
        Subject subject = SecurityUtils.getSubject();
        Users users = (Users) subject.getPrincipal();
        model.addAttribute("name","hello "+users.getUsername());
        return "test";
    }

    @RequestMapping("/add")
    public String add(){
        log.info("进入新增页面");
        //表示定位到user文件下的add页面
        return "user/add";
    }
    @RequestMapping("/update")
    public String update(){
        log.info("进入更新页面");
        //表示定位到user文件下的update页面
        return "user/update";
    }




}
