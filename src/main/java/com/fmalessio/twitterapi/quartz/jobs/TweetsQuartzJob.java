package com.fmalessio.twitterapi.quartz.jobs;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

import com.fmalessio.twitterapi.entity.InterestType;
import com.fmalessio.twitterapi.entity.SearchedTweet;
import com.fmalessio.twitterapi.repository.SearchedTweetRepository;

@Component
public class TweetsQuartzJob extends QuartzJobBean {

	private static final int INTEREST_SEARCH_AMOUNT = 5;

	private Twitter twitter;
	private SearchedTweetRepository searchedTweetRepository;

	@Autowired
	public TweetsQuartzJob(@Value("${twitter.key}") String twitterKey,
			SearchedTweetRepository searchedTweetRepository) {
		byte[] decodedBytes = Base64.getDecoder().decode(twitterKey);
		String decodedString = new String(decodedBytes);
		String[] keys = decodedString.split("#");
		twitter = new TwitterTemplate(keys[0], keys[1]);

		this.searchedTweetRepository = searchedTweetRepository;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getMergedJobDataMap();

		Long interestId = jobDataMap.getLong("interestId");
		String interestValue = jobDataMap.getString("interestValue");
		InterestType interestType = InterestType.valueOf(jobDataMap.getString("interestType"));

		List<Tweet> tweets = new ArrayList<Tweet>();
		if (InterestType.HASHTAG.equals(interestType)) {
			tweets = getTweetsByHashtag(interestValue);

		} else if (InterestType.USER.equals(interestType)) {
			tweets = getTweetsByUser(interestValue);
		}

		saveTweets(tweets, interestId);

	}

	private List<Tweet> getTweetsByHashtag(String hashtag) {
		List<Tweet> tweets = twitter.searchOperations().search(hashtag, INTEREST_SEARCH_AMOUNT).getTweets();
		return tweets;
	}

	private List<Tweet> getTweetsByUser(String user) {
		List<Tweet> tweets = twitter.timelineOperations().getUserTimeline(user, INTEREST_SEARCH_AMOUNT);
		return tweets;
	}

	private void saveTweets(List<Tweet> tweets, Long interestId) {
		SearchedTweet searchedTweet;
		for (Tweet tweet : tweets) {
			searchedTweet = new SearchedTweet(tweet, interestId);
			searchedTweetRepository.save(searchedTweet);
		}
		searchedTweetRepository.flush();
	}

}