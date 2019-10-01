package com.aud.demo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import springTestApp.src.main.java.com.aud.demo.configuration.Exception;
import springTestApp.src.main.java.com.aud.demo.configuration.Override;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
				
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
					.antMatchers("/").permitAll()
					.antMatchers("/login").permitAll()
					.antMatchers("/registration").permitAll()
					.antMatchers("/validate").permitAll()
					.antMatchers("/test").permitAll()
					.antMatchers("/reviewer/registration").permitAll()
					.antMatchers("/validateUser").permitAll()
					.antMatchers("/forgotPassword").permitAll()
					.antMatchers("/author").hasAnyAuthority(new String[] {"AUTHOR","ADMIN"})
					.antMatchers("/admin").hasAuthority("ADMIN")
					.and().authorizeRequests().anyRequest()
	                .authenticated()
					.and()
					.formLogin()
						.loginPage("/login")
						.failureUrl("/login?error=true")
						.defaultSuccessUrl("/").permitAll()
						.usernameParameter("email")
						.passwordParameter("password")
				.and()
					.logout()
					.deleteCookies("remove")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring() // we allow page to access these resources for these no need to authenticate
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}


}


