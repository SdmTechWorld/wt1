package com.example.ProduitJPA.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("sdm").password("{noop}123").roles("USER","ADMIN");
        auth.inMemoryAuthentication().withUser("singou").password("{noop}321").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.formLogin().loginPage("/login");
       http.authorizeRequests().antMatchers("/user/*").hasRole("USER");
       http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
        http.exceptionHandling().accessDeniedPage("/403");
        //http.csrf().disable();  pour desactive l'authentification csrf
    }
}
