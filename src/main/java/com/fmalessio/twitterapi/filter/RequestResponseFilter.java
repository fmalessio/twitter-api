package com.fmalessio.twitterapi.filter;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fmalessio.twitterapi.quartz.jobs.TweetsQuartzJob;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Component
@Order(1)
public class RequestResponseFilter implements Filter {

	@Value("${authentication.on}")
	private boolean checkAuthentication;
	private String APP_CLIENT_ID = "425080559212-0futf7p75r8kcl8lv0n72iiiqo6ktt2f.apps.googleusercontent.com";

	Logger logger = LoggerFactory.getLogger(TweetsQuartzJob.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		logger.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());

		if (checkAuthentication) {
			boolean isValidToken = checkAuthentication(req.getParameter("token"));
			if (!isValidToken) {
				res.setStatus(HttpStatus.BAD_REQUEST.value());
				return;
			}
		}
		// Go
		chain.doFilter(request, response);
		logger.info("Logging Response :{}", res.getContentType());
	}

	private boolean checkAuthentication(String token) {
		if (StringUtils.isEmpty(token)) {
			return false;
		}

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
				JacksonFactory.getDefaultInstance()).setAudience(Collections.singletonList(APP_CLIENT_ID)).build();

		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(token);
		} catch (GeneralSecurityException | IOException e) {
			return false;
		}
		if (idToken == null) {
			return false;
		}
		Payload payload = idToken.getPayload();

		// Print user identifier
		String userId = payload.getSubject();
		logger.info("User: " + userId + ", " + payload.getEmail() + ", " + Boolean.valueOf(payload.getEmailVerified())
				+ ", " + payload.get("name") + ", " + payload.get("picture") + ", " + payload.get("locale") + ", "
				+ payload.get("family_name") + ", " + (String) payload.get("given_name"));

		return true;
	}

}
