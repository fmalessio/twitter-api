package com.fmalessio.twitterapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmalessio.twitterapi.entity.Interest;
import com.fmalessio.twitterapi.repository.InterestRepository;

@Service
public class InterestsServiceImpl implements InterestsService {

	private InterestRepository interestRepository;
	private TweetsService tweetsService;

	@Autowired
	public InterestsServiceImpl(InterestRepository interestRepository, TweetsService tweetsService) {
		this.interestRepository = interestRepository;
		this.tweetsService = tweetsService;
	}

	@Override
	public Interest create(Interest interest) {
		interest = interestRepository.saveAndFlush(interest);
		tweetsService.runScheduler(interest);
		return interest;
	}

	@Override
	public void delete(long id) {
		Optional<Interest> interest = interestRepository.findById(id);
		if (interest.isPresent()) {
			tweetsService.deleteTweetsByInterestId(id);
			interestRepository.deleteById(id);
		}
	}

	@Override
	public void deleteAllByIds(List<Long> ids) {
		List<Interest> interests = interestRepository.findAllById(ids);
		for (Interest interest : interests) {
			tweetsService.deleteTweetsByInterestId(interest.getId());
			interestRepository.deleteById(interest.getId());
		}
	}

}
