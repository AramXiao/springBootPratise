package org.spring.web.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("huangxiaoming").roles("admin").password("$2a$10$okTI.y19eg8/Cysw6DI3FeSCF2R.adZ3hfe4zQC604JQ/TSF4W3eq").and()
                .withUser("admin").roles("readonly").password("$2a$10$nteCXb7sWv3iaQxmTvWTluo40b0iTvmUZfQdE/rAUhOvz9gkUwi0G");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/users/**");
        web.ignoring().antMatchers("/static/**");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//开启登录配置
        .antMatchers("/hello").hasRole("admin")//访问hello接口需要具备admin角色
        .anyRequest().authenticated()//剩余接口登录之后才能访问
        .and().formLogin()
        .loginPage("/login") //定义登录页面，未登录时，需要访问一个需要登录的接口，会自动跳转到该页面
        //登录处理接口
        .loginProcessingUrl("/doLogin")//定义处理接口
        .usernameParameter("username")//定义登录的用户名key，默认为username
        .passwordParameter("password")//定义登录的密码key，默认为password

        .successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                System.out.println("Entry onAuthenticationSuccess");
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("success");
                out.flush();
            }
        })//登录成功的处理器
        .failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
                System.out.println("Entry onAuthenticationFailure");
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("fail");
                out.flush();
            }
        })//登录失败的处理器
        .permitAll()//和表单登录相关的接口统统都通过
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("logout success");
                out.flush();
            }
        })
        .permitAll()
        .and()
        .httpBasic()
        .and()
        .csrf().disable();

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
