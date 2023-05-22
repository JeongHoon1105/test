package com.tapanda.tapanda.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.tapanda.tapanda.enum_.RoleName;

import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;
	@Autowired
    private LoginSuccessHandler successUserHandler;

    /*~~(Migrate manually based on https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)~~>*/
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        http.csrf().disable();
        http.authorizeHttpRequests()
                                .antMatchers("/admin/**").hasAuthority(RoleName.ROLE_ADMIN.toString())
                                .antMatchers("/admin/**").authenticated()
                                .anyRequest().permitAll()
                            .and()
                            .formLogin()
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                				.failureUrl("/login?error")
                				//.loginProcessingUrl("/dologin")
                                .successHandler(successUserHandler)
                            .and()
                                .logout()
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID", "remember-me")
                                .addLogoutHandler(new LogoutHandler() {
                                    @Override
                                    public void logout(HttpServletRequest request, 
                                                     HttpServletResponse response, 
                                                     Authentication authentication) {
                                        HttpSession session = request.getSession();
                                        session.invalidate();
                                        }
                                    })
                                .logoutSuccessHandler(new LogoutSuccessHandler() {
                                    @Override
                                    public void onLogoutSuccess(HttpServletRequest request, 
                                                     HttpServletResponse response, 
                                                     Authentication authentication) throws IOException, ServletException {
                                        response.sendRedirect("/login");
                                        }
                                    })
                                .deleteCookies("remember-me").and().httpBasic();
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
   
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}