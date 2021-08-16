package com.connection.oracle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.connection.oracle.filter.JwtFilter;
import com.connection.oracle.repository.serviceImpl.PersonDaoServiceImpl;


@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PersonDaoServiceImpl service;
	@Autowired
    private JwtFilter jwtFilter;

	@Override // We override method of WebSecurityConfiadap class so that we can change
				// default security config
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service); // es se bate te hai konsa atutenication data base auth ya in
															// memory.
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// es method se define karte hai consi API public karna hai ya fr private
		// we will write konse URL ko authorize karna hai
		 http.csrf().disable().authorizeRequests().antMatchers("/api/authenticate")
         .permitAll().anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	 // Ye AM ka object spring contain ko dekar or fr sping container service class m
			// autowire kardega
	 @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
