package com.example.demo.config;


import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.controller.CostTypeController;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {


	public static final Logger logger = LoggerFactory.getLogger(CostTypeController.class);
	
	@Autowired
    private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/login/").permitAll().anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll().and()
				.logout().permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/app/api/auth/logout"))
				.logoutSuccessUrl("/login").and().exceptionHandling()
				.accessDeniedPage("/access-denied")
				.and()
			.exceptionHandling()
			.accessDeniedPage("/app/error");
	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select * from users where email=?")
		.authoritiesByUsernameQuery("select * from permtypes where permname=?");
				
	}
	
	
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {

        authenticationMgr.inMemoryAuthentication().withUser("a").password("{noop}a")
            .authorities("ROLE_USER").and().withUser("b").password("{noop}b")
            .authorities("ROLE_USER", "ROLE_ADMIN");
    }
    
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
   
    
}
