package com.fmalessio.twitterapi.service;

public interface TweetsService {

	public void runScheduler();

	public void removeJob(String jobName);

}
