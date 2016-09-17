package cn.sheep3.util;

import cn.sheep3.entity.User;
import cn.sheep3.exception.UserException;
import cn.sheep3.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheep3 on 16-9-17.
 */
@Slf4j
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userSrv;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = null;
        try {
            user = userSrv.findByUserLogin(username);
        } catch (UserException e) {
            log.error("系统错误:"+e.getMessage());
            throw new BadCredentialsException("Username not found.");
        }
        if(user == null){
            throw new BadCredentialsException("Username not found.");
        }
        log.error("有人尝试登陆，用户名为:"+username+",密码为:"+password);
        //加密过程在这里体现
        if (!PassWordUtil.getMD5(password+user.getUserSalt()).equals(user.getUserPass()) ) {
            throw new BadCredentialsException("Wrong password.");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
