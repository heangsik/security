package kr.co.yhs.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.persistence.Entity;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain initFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .mvcMatchers("/", "/acount/**", "/h2-console/**").permitAll()
                .mvcMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated();
//                .anyRequest().permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.formLogin();
        http.logout().logoutSuccessUrl("/");
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer initWebSecurity() {
        return web -> {
            web.ignoring().mvcMatchers("/web/ignor/**");
        };
    }

    @Bean
    public PasswordEncoder initPassEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
