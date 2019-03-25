package com.fan.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro的配置类
 * @author ruanjiayu
 * @dateTime 2019/3/20 14:46
 */

@Configuration
public class ShiroConfig {

    @Bean
   public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
       shiroFilterFactoryBean.setSecurityManager(securityManager);

       //添加Shiro内置过滤器
        /**
         * shiro的过滤器可以实现权限相关的拦截器
         *      常用的过滤器：
         *      anon:无需认证登录就可以访问
         *      authc:必须认证才能访问
         *      user:如果使用rememberMe的功能可以直接访问
         *      perms:该资源必须得到角色权限才能访问
         *      role:该资源必须得到角色权限才能访问
         */

        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        //进行拦截以及放通的url
        filterMap.put("/toLogin","anon");
        filterMap.put("/login","anon");
        //授权过滤器
        filterMap.put("/user/add","perms[add]");
        filterMap.put("/user/update","perms[update]");
        //访问user路径下的都需要登录
        filterMap.put("/user/*","authc");


        //修改登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


       return shiroFilterFactoryBean;
   }

    /**
     * 创建DefaultWebSecurityManager
     * @param userRealm
     * @return
     */
    @Bean(name = "securityManager")
   public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
       securityManager.setRealm(userRealm);
       return securityManager;
   }

    /**
     * 创建Realm
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }


    /**
     * 配置ShiroDialect,用于thymeleaf和shiro标签配合使用
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
