package com.fmalessio.twitterapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.web.context.request.RequestContextHolder;

@Configuration
public class SocialConfig extends SocialConfigurerAdapter {

	@Override
	public UserIdSource getUserIdSource() {
		return new SessionIdUserIdSource();
	}

	private static final class SessionIdUserIdSource implements UserIdSource {
		@Override
		public String getUserId() {
			return RequestContextHolder.currentRequestAttributes().getSessionId();
		}
	}

}