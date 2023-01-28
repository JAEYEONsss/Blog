package com.jojoldu.book.springboot.domain.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity//spring security 설정들을 활성화시켜 준다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())//antMatchers는 권한 관리 대상을 지정하는 옵션 ,/api/v1/** 주소는 USER 권한만 접근 가능
                .anyRequest().authenticated().and()//anyRequest는 위에 설정한 url 이외에 나머지 것들을 의미한다
                .logout().logoutSuccessUrl("/").and().//logout()은 로그아웃 기능에 대한 여러 설정의 진입점, 로그아웃 성공 시 / 주소로 이동
                oauth2Login().defaultSuccessUrl("/", true).and().//로그인 이후 홈 화면으로 리다이렉트 없으면 script 파일이 출력되었음
                oauth2Login().//OAuth2 로그인 기능에 대한 여러 설정의 진입점
                userInfoEndpoint().//OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                userService(customOAuth2UserService);
        /**
         * userService
         * 소셜 로그인 성공 시 후속 조치를 진행 할 UserService 인터페이스의 구현체를 등록
         * 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 사애에서 추가로 진행하고자 하는 기능 명시 가능
         * **/
    }
}
