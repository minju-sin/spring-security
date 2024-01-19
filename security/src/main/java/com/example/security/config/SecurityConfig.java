package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 이 클래스는 config용 이라는 것을 나타냄
@EnableWebSecurity  //  시큐리티라는 것을 나타내기 위해 작성
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );

//        http
//                .formLogin((auth)-> auth.loginPage("/login")
//                        .loginProcessingUrl("/loginProc")
//                        .permitAll()
//                );

        http
                .httpBasic(Customizer.withDefaults());

        //http
        //        .csrf((auth) -> auth.disable());

        http
                .sessionManagement((auth) ->auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));

        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());


        return http.build();
    }

}
