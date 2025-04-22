package com.example.haisya_manager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/css/**", "/js/**", "/", "/admin/login").permitAll()
				.requestMatchers("/admin/top").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/admin/login")			// ログイン画面
				.loginProcessingUrl("/admin/login")	// 認証処理を受付させる
				.defaultSuccessUrl("/admin/top", true)	// 認証成功後は配車係のトップ画面に
				.failureUrl("/admin/login?error")
				.permitAll()
			)
			.logout((logout) -> logout
				.logoutSuccessUrl("/?loggedOut")
				.permitAll()
			);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
