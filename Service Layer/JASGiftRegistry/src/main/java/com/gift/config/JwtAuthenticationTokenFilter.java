package com.gift.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gift.model.User;
import com.gift.service.UserDetailsServiceImpl;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private final UserDetailsServiceImpl userDetailsService;

	private static final String tokenValidationURL = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";
	private static final String microServicesURL = "http://localhost:9090/";
	private static final String defaultPassword = "password";

	public JwtAuthenticationTokenFilter(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		final String requestHeader = request.getHeader("Authorization");

		String username = null;
		String email = null;
		if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
			String authToken = requestHeader.substring(7);
			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<String> googleResponse = restTemplate.getForEntity(tokenValidationURL + authToken,
					String.class);

			HttpStatus status = googleResponse.getStatusCode();
			if (status.is2xxSuccessful()) {
				String userDetails = googleResponse.getBody();

				ObjectMapper mapper = new ObjectMapper();

				JsonNode root = mapper.readTree(userDetails);

				email = root.path("email").toString().replace("\"", "").trim();
				username = root.path("name").toString().replace("\"", "").trim();

			} else {
				logger.info("Google token invalid or expired");
			}
			logger.info("Status " + googleResponse.getStatusCode());

		} else {
			logger.info("couldn't find bearer string, will ignore the header");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			logger.info("checking authentication for user " + username);
			try {

				final String signUpHeader = request.getHeader("Signup");
				System.out.println("Signup " + signUpHeader);

				if (signUpHeader != null && signUpHeader.equalsIgnoreCase("true")) {
					RestTemplate restTemplate = new RestTemplate();

					User newUser = new User(username, email, new BCryptPasswordEncoder().encode(defaultPassword));
					restTemplate.postForEntity(microServicesURL + "create", newUser, User.class).getBody();

					logger.info("In User Create" + username);
					response.setStatus(201);
				}
				UserDetails userDetails = userDetailsService.loadUserByUsername(email);

				System.out.println("Woohoo!" + userDetails.getUsername());
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("User not found");
			}
		}

		chain.doFilter(request, response);
	}

}
