package com.goodreadsclone.goodreadsclone.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityAdapter extends WebSecurityConfigurerAdapter {

        @Override
public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("style.css","/css/**","/js/**");
}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // @formatter:off
       http
       .authorizeRequests(a -> a
           .antMatchers("/","/webjars/**")
           
           .permitAll()
           .anyRequest().authenticated()
       )
       .csrf(c -> c
       .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
   )
       .exceptionHandling(e -> e
           .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
       )
       .logout(l -> l
            .logoutSuccessUrl("/").permitAll()
        )
       .oauth2Login();
   // @formatter:onF
    }

}
