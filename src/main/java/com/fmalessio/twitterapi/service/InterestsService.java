package com.fmalessio.twitterapi.service;

import com.fmalessio.twitterapi.entity.Interest;

public interface InterestsService {

	void create(Interest interest);

	void delete(long id);

}
