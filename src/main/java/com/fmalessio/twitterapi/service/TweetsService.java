package com.fmalessio.twitterapi.service;

import com.fmalessio.twitterapi.entity.Interest;

public interface TweetsService {

	public void runScheduler(Interest interest);

	public void removeJobByInterestId(String interestId);

}
