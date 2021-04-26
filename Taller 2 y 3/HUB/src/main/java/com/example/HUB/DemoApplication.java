package com.example.HUB;

import com.example.security.JWTAuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@EnableJpaRepositories("com.example.repositories")
@EntityScan("com.example.entities")

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception
		{
			http.csrf().disable()
					.addFilterAfter(new JWTAuthFilter(), UsernamePasswordAuthenticationFilter.class )
					.authorizeRequests()
					.antMatchers(HttpMethod.POST,"/Login").permitAll()
					.antMatchers(HttpMethod.GET,"/").permitAll()
					.antMatchers("/v3/api-docs",
							"/v3/api-docs/**",
							"/configuration/ui",
							"/swagger-resources/**",
							"/configuration/security",
							"/swagger-ui.html",
							"/swagger-ui/**",
                                                        "/typePainting",
                                                        "/costsRestoration",
                                                        "/costsRestoration/**",
							"/webjars/**").permitAll()
					.anyRequest().authenticated();

		}
	}

}
