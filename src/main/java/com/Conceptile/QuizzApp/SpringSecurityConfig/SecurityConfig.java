package com.Conceptile.QuizzApp.SpringSecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    UserDetailsService getUDS(){
        return new CustomUserDetailsService();
    }
    @Bean
    AuthenticationProvider authProvider(UserDetailsService usd, PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(getUDS());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity https) throws Exception{
        return https
            .csrf(c->c.disable())
            .headers(h-> h.frameOptions(frames-> frames.disable()))
            .authorizeHttpRequests(c->c.requestMatchers("/u/register","/h2-console/**","/").permitAll()
                .anyRequest().authenticated())
            .formLogin(form-> form.defaultSuccessUrl("/h2-console").permitAll())
            .httpBasic(h-> h.init(https))
            .build();
    }
}
