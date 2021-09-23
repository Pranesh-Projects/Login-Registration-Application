package com.example.login_registration.config;

import com.example.login_registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService service;

    @Autowired
    public SecurityConfiguration(UserService service) {
        this.service = service;
    }

    //    Using 'BCryptPasswordEncoder' to encode password
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    to integrate 'hibernate' and 'Spring Data JPA' in 'Spring Security'
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(service);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

//    pass 'authenticationProvider' to 'configure' method. So override one more 'configure' method
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

//    Overriding 'login' page
    @Override
    protected void configure(HttpSecurity http) throws Exception{
//        URL that need authorization
        http.authorizeRequests().antMatchers(
                "/registration**",
                "/js/**",
                "/css/**",
                "/img/**"
        ).permitAll()               //     permit authorization for all
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                authorization for login
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
//                authorization for logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                authorization for login after logout
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
}
