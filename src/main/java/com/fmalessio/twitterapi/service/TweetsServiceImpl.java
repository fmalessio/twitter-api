package com.fmalessio.twitterapi.service;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

@Service
public class TweetsServiceImpl implements TweetsService {

	private Twitter twitter;

	@Autowired
	public TweetsServiceImpl(@Value("${twitter.consumerKey}") String twitterConsumerKey,
			@Value("${twitter.consumerSecret}") String twitterConsumerSecret) {
		twitter = new TwitterTemplate(twitterConsumerKey, twitterConsumerSecret);
	}

	public List<Tweet> getTweetsByHashtag(final String hashtag, final int amount) {
		List<Tweet> tweets = twitter.searchOperations().search(hashtag, amount).getTweets();

		// Remove duplicates, move this to the wrapper search
		tweets = tweets.stream().collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(Tweet::getId))), ArrayList::new));
		
		return tweets;
	}

	public List<Tweet> getTweetsByUser(final String user, final int amount) {
		List<Tweet> tweets = twitter.timelineOperations().getUserTimeline(user, amount);
		return tweets;
	}

}
