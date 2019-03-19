package com.fmalessio.twitterapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmalessio.twitterapi.entity.User;
import com.fmalessio.twitterapi.repository.UserRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public String getUserInfo(String id) {
		Optional<User> user = findUserById(id);
		if (user.isPresent()) {
			return user.toString();
		}
		return "User not exist.";
	}

	@Override
	public Optional<User> findUserById(String id) {
		Optional<User> user = userRepo.findById(id);
		return user;
	}

}
