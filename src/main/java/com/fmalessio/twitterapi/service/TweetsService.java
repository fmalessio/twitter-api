package com.fmalessio.twitterapi.service;

import java.util.List;

import org.springframework.social.twitter.api.Tweet;

public interface TweetsService {

	public List<Tweet> getTweetsByHashtag(final String hashTag, final int amount);

	public List<Tweet> getTweetsByUser(final String user, final int amount);

}
