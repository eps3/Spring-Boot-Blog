package cn.sheep3.config;

import cn.sheep3.util.MyAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by sheep3 on 16-9-17.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定义的密码验证策略
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new MyAuthenticationProvider();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**","/css/**","/fonts/**","/images/**","/js/**","/lib/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
            .antMatchers("admin","/admin/**").authenticated()
            .anyRequest().permitAll()
            .and()
        .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/index")
            .permitAll()
            .and()
        .logout()
            .permitAll()
            .and()
        .csrf().disable()
        ;
    }

}
