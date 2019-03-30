package com.fmalessio.twitterapi.service;

import java.util.List;

import com.fmalessio.twitterapi.entity.Interest;

public interface InterestsService {

	Interest create(Interest interest);

	void delete(long id);

	void deleteAllByIds(List<Long> ids);

}
