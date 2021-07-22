//package com.connection.oracle.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.connection.oracle.repository.serviceImpl.MyUserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//public class MySecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private MyUserDetailsService customUserDetailsService;
//
//	@Override // We override method of WebSecurityConfiadap class so that we can change
//				// default security config
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customUserDetailsService); // es se bate te hai konsa atutenication data base auth ya in
//															// memory.
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// es method se define karte hai consi API public karna hai ya fr private
//		http.csrf().disable().cors().disable().authorizeRequests() // we will write konse URL ko authorize karna hai
//				.antMatchers("/token").permitAll().anyRequest().authenticated();// .and().sessionManagement()
//		// .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//
//	@Bean // Ye AM ka object spring contain ko dekar or fr sping container service class m
//			// autowire kardega
//	@Override
//	public AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//}
