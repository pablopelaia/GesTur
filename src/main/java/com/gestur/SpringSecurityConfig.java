package com.gestur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gestur.auth.handler.LoginSuccessHandler;
import com.gestur.services.EmpleadoService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccessHandler successHandler;

	@Autowired
	private EmpleadoService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {

		PasswordEncoder encoder = passwordEncoder();

		builder.userDetailsService(userDetailsService).passwordEncoder(encoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()

				.antMatchers("/", "/index", "/css/**", "/js/**", "/contactform/**", "/fonts/**", "/img/**", "/lib/**",
						"/vendor/**")

				.permitAll().antMatchers("/empleado/**").hasRole("ADMIN")
				/* .antMatchers("/reserva/**").hasAnyRole("USER") */.anyRequest().authenticated().and().formLogin()
				.successHandler(successHandler).loginPage("/login").permitAll().and().logout().permitAll();
	}

}
