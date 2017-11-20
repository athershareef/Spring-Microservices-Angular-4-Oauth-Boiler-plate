package com.gift.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.gift.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext oauth2ClientContext;
	@Autowired
	AuthorizationCodeResourceDetails authorizationCodeResourceDetails;
	@Autowired
	ResourceServerProperties resourceServerProperties;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	// This method is for overriding some configuration of the WebSecurity
	// If you want to ignore some request or request patterns then you can
	// specify that inside this method
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	private JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
		return new JwtAuthenticationTokenFilter(userDetailsService);
	}

	private OAuth2ClientAuthenticationProcessingFilter filter() {
		// Creating the filter for "/google/login" url
		OAuth2ClientAuthenticationProcessingFilter oAuth2Filter = new OAuth2ClientAuthenticationProcessingFilter(
				"/google/login");

		// Creating the rest template for getting connected with OAuth service.
		// The configuration parameters will inject while creating the bean.
		System.out.println("athers");
		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(authorizationCodeResourceDetails,
				oauth2ClientContext);
		oAuth2Filter.setRestTemplate(oAuth2RestTemplate);

		// setting the token service. It will help for getting the token and
		// user details from the OAuth Service
		oAuth2Filter.setTokenServices(new UserInfoTokenServices(resourceServerProperties.getUserInfoUri(),
				resourceServerProperties.getClientId()));
		System.out.println("atherrr" + resourceServerProperties.getClientId());
		return oAuth2Filter;
	}

	// This method is used for override HttpSecurity of the web Application.
	// We can specify our authorization criteria inside this method.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/", "/user/login")
				.permitAll().antMatchers("/user/create", "/user/login", "/user/signout", "/user/forgot").permitAll()
				.anyRequest().authenticated().and().httpBasic().and().logout().clearAuthentication(true)
				.logoutSuccessUrl("/user/signout").deleteCookies("JSESSIONID").invalidateHttpSession(true).and()
				.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
				.addFilterAt(filter(), BasicAuthenticationFilter.class);

		// logout()
		// .logoutRequestMatcher(new
		// AntPathRequestMatcher("/logout")).logoutSuccessUrl("/logout/success").and()
		//
		// http
		// // starts authorizing configurations
		// .authorizeRequests()
		// // ignore the "/" and "/index.html"
		// // .antMatchers("/", "/**.html", "/**.js").permitAll()
		// // authenticate all remaining URLS
		// .anyRequest().fullyAuthenticated()//
		// .and()//
		// // setting the logout URL "/logout" - default logout URL
		// .logout()//
		// // after successful logout the application will redirect to "/"
		// // path
		// .logoutSuccessUrl("/")//
		// .permitAll()//
		// .and()//
		// // Setting the filter for the URL "/google/login"
		// .addFilterAt(filter(), BasicAuthenticationFilter.class)//
		// .csrf()//
		// .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}
