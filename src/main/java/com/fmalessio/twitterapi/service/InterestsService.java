package com.fmalessio.twitterapi.service;

import com.fmalessio.twitterapi.entity.Interest;

public interface InterestsService {

	Interest create(Interest interest);

	void delete(long id);

}
