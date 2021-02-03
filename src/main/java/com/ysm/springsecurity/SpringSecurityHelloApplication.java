package com.ysm.springsecurity;

import java.util.function.Function;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.FilterChainProxy;

@SpringBootApplication
public class SpringSecurityHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityHelloApplication.class, args);
		
	}

}

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Bean
	public UserDetailsManager getUserDetailsService()
	{
		 return new InMemoryUserDetailsManager();
	}
	
	@Bean
	public InitializingBean init(UserDetailsManager manager)
	{
		 return new InitializingBean() {
			
			@Override
			public void afterPropertiesSet() throws Exception {
				Function passwordencoder= t -> t;
				
				manager.createUser(User.withDefaultPasswordEncoder().username("mittal").password("1234").roles("USER").build());
				manager.createUser(User.withDefaultPasswordEncoder().username("mittalshah").password("12345").roles("USER").build());
				
			}
		};
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.httpBasic();
	http.authorizeRequests().anyRequest().authenticated();
	
	}
}