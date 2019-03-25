package com.fan.shiro;

import com.fan.domain.Users;
import com.fan.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 实现自己定义的Realm
 * @author ruanjiayu
 * @dateTime 2019/3/20 14:50
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UsersService usersService;

    /**
     * 需要权限的页面都会进入下面的doGetAuthorizationInfo方法来，确定一下是否具有权限
     * 不需要权限的页面不会进入
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行授权认证逻辑++");

        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        Users users = (Users) subject.getPrincipal();

        Users dbUser = usersService.findById(users.getId());

        Set<String> set = new HashSet<>();

        set.add(dbUser.getPermission());
        //添加资源的授权字符串
        info.setStringPermissions(set);
        return info;
    }

    /**
     * 执行身份认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("执行身份认证逻辑");

        /**编写shiro判断逻辑，判断用户名和密码*/
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        Users users = usersService.findByName(token.getUsername());
        if(users == null){
            //用户名不存在
            //shiro底层会抛出UnKnowAccountException
            return null;
        }
        //2.判断密码
        /**
         * 我来解释一下三个参数
         * 第一个参数表示是 从服务器里面取得的用户，将其放入SecurityUtils.getSubject()中，可以通过SecurityUtils.getSubject().getPrincipal()
         * 第二个参数表示 服务器里面的密码
         * 第三个参数可有可无
         */
        return new SimpleAuthenticationInfo(users,users.getPassword(),"");
    }
}
