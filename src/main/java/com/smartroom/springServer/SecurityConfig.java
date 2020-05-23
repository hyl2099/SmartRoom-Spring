package com.smartroom.springServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//spring security是spring家族的一个安全框架
//自动装配 spring 身份验证管理器
//作用： 防止CSRF攻击
//因为spring security有自己默认的登录页

//web的安全控制一般分为两个部分，一个是认证，一个是授权。
//认证管理：
//就是认证是否为合法用户，简单的说是登录。一般为匹对用户名和密码，即认证成功。

//当用户点击登录时，
//1.它会拿到用户输入的用户名密码；
//2.根据用户名通过UserDetailsService 的 loadUserByUsername(username)方法获得一个用户对象；
//3.获得一个UserDetails 对象，获得内部的成员属性password；
//4.通过PasswordEncoder 的 matchs(s1, s2) 方法对比用户的输入的密码和第3步的密码；
//5.匹配成功；

//1.实现UserDetailsService ，可以选择同时实现用户的正常业务方法和UserDetailsService ；
//2.实现UserDetails ，一般使用用户的实体类实现此接口。
//3.实现PasswordEncoder ，spring security 提供了多个该接口的实现类，可百度和查看源码理解，也可以自己写。

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Qualifier("smartroom.users")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilter(jwtAuthorizationFilter());
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(this.authenticationManager());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
