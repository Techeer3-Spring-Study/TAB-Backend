package com.techeeresc.tab.domain.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity //기본적인 웹 보안 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web){
        web
                .ignoring()
                .antMatchers(
                        "/**" //"/h2-console/**"
                        ,"/favicon.ico"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //httpservletrequest를 사용하는 요청들에 대한 접근 제한 설정
                .antMatchers("/**").permitAll() //해당 URl에 대한 요청은 인증
                .anyRequest().authenticated(); //나머지 url은 모두 인증돼야한다
    }
}
