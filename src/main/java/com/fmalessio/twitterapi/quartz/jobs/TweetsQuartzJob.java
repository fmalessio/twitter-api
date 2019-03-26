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

@Component
public class TweetsQuartzJob extends QuartzJobBean {

	private Twitter twitter;

	private static final int INTEREST_SEARCH_AMOUNT = 5;

	@Autowired
	public TweetsQuartzJob(@Value("${twitter.key}") String twitterKey) {
		byte[] decodedBytes = Base64.getDecoder().decode(twitterKey);
		String decodedString = new String(decodedBytes);
		String[] keys = decodedString.split("#");
		twitter = new TwitterTemplate(keys[0], keys[1]);
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getMergedJobDataMap();

		String value = jobDataMap.getString("interestValue");
		InterestType type = InterestType.valueOf(jobDataMap.getString("interestType"));

		List<Tweet> tweets = new ArrayList<Tweet>();
		if (InterestType.HASHTAG.equals(type)) {
			tweets = getTweetsByHashtag(value);
		} else if (InterestType.USER.equals(type)) {
			tweets = getTweetsByHashtag(value);
		}

		System.out.println(tweets.get(0).getId());
		// TODO: save tweets searched in data base
	}

	public List<Tweet> getTweetsByHashtag(String hashtag) {
		List<Tweet> tweets = twitter.searchOperations().search(hashtag, INTEREST_SEARCH_AMOUNT).getTweets();
		return tweets;
	}

	public List<Tweet> getTweetsByUser(String user) {
		List<Tweet> tweets = twitter.timelineOperations().getUserTimeline(user, INTEREST_SEARCH_AMOUNT);
		return tweets;
	}

}