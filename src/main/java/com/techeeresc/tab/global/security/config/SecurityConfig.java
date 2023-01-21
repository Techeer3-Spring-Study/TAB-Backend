package com.techeeresc.tab.global.security.config;

import com.techeeresc.tab.global.security.jwt.JwtAccessDeniedHandler;
import com.techeeresc.tab.global.security.jwt.JwtAuthenticationEntryPoint;
import com.techeeresc.tab.global.security.jwt.JwtSecurityConfig;
import com.techeeresc.tab.global.security.jwt.TokenProvider;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//
////
//@EnableWebSecurity //기본적인 웹 보안 활성화
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(WebSecurity web){
//        web
//                .ignoring()
//                .antMatchers(
//                        "/**" //"/h2-console/**"
//                        ,"/favicon.ico"
//                );
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests() //httpservletrequest를 사용하는 요청들에 대한 접근 제한 설정
//                .antMatchers("/**").permitAll() //해당 URl에 대한 요청은 인증
//                .anyRequest().authenticated(); //나머지 url은 모두 인증돼야한다
//    }
//}
////
@EnableWebSecurity
@EnableMethodSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    //private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(
            TokenProvider tokenProvider,
            //CorsFilter corsFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        //this.corsFilter = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//       http
                // token을 사용하는 방식이기 때문에 csrf를 disable
                .csrf().disable()

                //.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests()
                //.antMatchers("/api/v1/member/signup", "/api/v1/authenticate").permitAll()
                //.requestMatchers("/api/v1/authenticate","api/").permitAll()
                //.requestMatchers(PathRequest.toH2Console()).permitAll()
                .antMatchers("/api/v1/**", "/api/v1/authenticate").permitAll()
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return httpSecurity.build();
    }
}