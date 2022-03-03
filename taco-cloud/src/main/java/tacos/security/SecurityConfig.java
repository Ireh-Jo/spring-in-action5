package tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
			.antMatchers("/", "/**").access("permitAll")
		.and()
			.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth.inMemoryAuthentication() .withUser("user1") .password("{noop}password1")
		 * .authorities("ROLE_USER") .and() .withUser("user2")
		 * .password("{noop}password2") .authorities("ROLE_USER");
		 */
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(
					"select username, password, enabled from users " +
					"where username=?")
			.authoritiesByUsernameQuery(
					"select username, autority from authorities " +
					"where username=?")
			.passwordEncoder(new NoEncodingPasswordEncoder());
	}
}
