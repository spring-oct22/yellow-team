package com.example.winery.auth;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import java.util.Collection;


@EnableGlobalMethodSecurity(securedEnabled = true)  //solo deja entrar donde el rol coincida con el usuario loggeado
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/**").permitAll()  //cualquier petición GET
                .mvcMatchers(HttpMethod.DELETE, "/**").fullyAuthenticated()  //cualquier petición DELETE
                .mvcMatchers(HttpMethod.POST, "/**").fullyAuthenticated()  //cualquier petición DELETE
                .mvcMatchers(HttpMethod.PUT, "/**").fullyAuthenticated()  //cualquier petición DELETE
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin();
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
               // .userDetailsContextMapper(userDetailsContextMapper()) //
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://localhost:8389/dc=springframework,dc=org")
                .and()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");
    }
   /* @Bean
    public UserDetailsContextMapper userDetailsContextMapper() {
        return new LdapUserDetailsMapper() {
            @Override
            public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
                UserDetails details = User.builder()
                        .username(username)
                        .password("")
                        .roles(username.equals("ben")?"ADMIN":"USER")
                        .build();
                return details;
            }
        };
    } */

}
