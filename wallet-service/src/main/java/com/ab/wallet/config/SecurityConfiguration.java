/**
 * 
 */
package com.ab.wallet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Bovas
 *
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/**")
			.permitAll()
			.and()
			.httpBasic().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {		
		web.ignoring().antMatchers("/**");
	}
	
}
