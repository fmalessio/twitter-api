package com.fmalessio.twitterapi.service;

import com.fmalessio.twitterapi.entity.Interest;

public interface TweetsService {

	void runScheduler(Interest interest);

	void deleteTweetsByInterestId(long interestId);

}
