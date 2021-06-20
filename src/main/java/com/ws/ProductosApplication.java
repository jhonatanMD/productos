package com.ws;

import com.ws.configuracion.jwt.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@SpringBootApplication
public class ProductosApplication {



	private static final String[] AUTH_WHITELIST = {
			"/swagger-resources/**",
			"/swagger-ui.html",
			"/v2/api-docs",
			"/webjars/**","/swagger-ui.html/**"
	};



	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProductosApplication.class, args);

	}


	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/token/login").permitAll()
					.antMatchers(HttpMethod.GET, "/api/generarReporte").permitAll()
					.antMatchers(HttpMethod.POST, "/api/consulta/consultar_voz").permitAll()
					.antMatchers(HttpMethod.GET, AUTH_WHITELIST).permitAll()
					.anyRequest().authenticated().and().cors();
		}
	}



}
