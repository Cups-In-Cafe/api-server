package com.highgroup.highgroup.config;

import com.highgroup.highgroup.config.jwt.JwtAuthenticationEntryPoint;
import com.highgroup.highgroup.config.jwt.JwtAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  public static String[] matchPatten = {
    "/v1/upload/**", // 파일 업로드 Path
    "/v1/admin/login",
    "/v1/admin/login_check",
    "/v1/user/**",
    "/v1/ws/**",
  };

  public static String[] matchPatten_TEST = {
    "/**",
  };

  @Value("${app.mode}")
  private String appMode;
  
  @Bean
  public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
    return new JwtAuthenticationFilter();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //if appMode eq TEST 일 경우 패턴 셋팅
    matchPatten = appMode.equals("TEST") ? matchPatten_TEST : matchPatten;

    http.cors()
        .and()
          .csrf().disable().authorizeRequests()
          .antMatchers(matchPatten).permitAll().anyRequest()
          .authenticated()
        .and()
          .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
        .and()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

  }

}

