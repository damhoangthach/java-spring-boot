package com.laptrinhjavaweb.config;

import com.laptrinhjavaweb.security.CustomSuccessHandler;
import com.laptrinhjavaweb.service.impl.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    // 1 phần có sẵn của security kiểu mã hóa là Bcrypt
    // hỗ trợ ta việc check password mã hóa
    // Và hỗ trợ khi đăng ký nó sẽ mã hóa giúp ta
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    // nơi xác thực username password
    // AuthenticationManagerBuilder là nơi quản lý các chứng(xác) thực
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()// đường dẫn phải đc yêu cầu chứng thực luôn luôn có
                //.antMatchers("/admin/**").authenticated()//chỉ cần đăng nhập là vào được
                //.antMatchers("/admin/building").authenticated()
                .antMatchers("/admin/building-list").authenticated()
                .antMatchers("/admin/building-edit").hasRole("MANAGER")  //thêm
                .antMatchers("/admin/building-edit?**").authenticated()  //cập nhật thì thèn vào cũng dc,thèn nào dc giao sẽ dc vào


                .antMatchers("/login", "/resource/**", "/trang-chu", "/api/**").permitAll()  // các paths = path như đã định nghĩa thì cho vào
                .and()
                .formLogin().loginPage("/login").usernameParameter("j_username").passwordParameter("j_password").permitAll()
                .loginProcessingUrl("/j_spring_security_check")  // path có sẵn của SS nó nhận username password vào  và trong path này đã khai báo AuthenticationManagerBuilder
                .successHandler(myAuthenticationSuccessHandler())   // nếu đúng thì nhảy tới page được định nghĩa
                .failureUrl("/login?incorrectAccount").and()
                .logout().logoutUrl("/logout").deleteCookies("JSESSIONID")// khi logout thì xóa cookiees
                .and().exceptionHandling().accessDeniedPage("/access-denied").and()  // là 1 controller
                .sessionManagement().maximumSessions(1).expiredUrl("/login?sessionTimeout");
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new CustomSuccessHandler();
    }
}
