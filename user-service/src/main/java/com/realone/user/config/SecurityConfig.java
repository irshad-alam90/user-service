package com.realone.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

	/*@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
			//.requestMatchers("/users")
			//.permitAll()
			//.anyRequest()
			//.authenticated()
			//.and()
			.formLogin(Customizer.withDefaults());
			//.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails userDetails = User.builder()
				.username("irshad")
				.password(passwordEncoder().encode("alam"))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(userDetails);
	}*/
}
