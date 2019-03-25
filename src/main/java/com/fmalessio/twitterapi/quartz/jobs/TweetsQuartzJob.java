package com.fmalessio.twitterapi.quartz.jobs;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.TreeSet;

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

@Component
public class TweetsQuartzJob extends QuartzJobBean {

	private Twitter twitter;

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

		int number = jobDataMap.getInt("number");
		String value = jobDataMap.getString("value");

		List<Tweet> test = getTweetsByHashtag(value, number);

		System.out.println(test);
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