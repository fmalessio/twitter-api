package com.fmalessio.twitterapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmalessio.twitterapi.entity.Interest;
import com.fmalessio.twitterapi.repository.InterestRepository;

@Service
public class InterestsServiceImpl implements InterestsService {

	@Autowired
	private InterestRepository interestRepository;

	@Override
	public void create(Interest interest) {
		interestRepository.saveAndFlush(interest);
	}

	@Override
	public void delete(long id) {
		interestRepository.deleteById(id);
	}

}
