package com.fmalessio.twitterapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fmalessio.twitterapi.entity.User;
import com.fmalessio.twitterapi.repository.UserRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User login(User userParam) {
		Optional<User> user = findUserById(userParam.getId());

		if (user.isPresent()) {
			// update last login
			return user.get();
		}

		// Create
		return createUser(userParam);
	}

	private User createUser(User user) {
		user = new User(user.getId(), user.getFullName());
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public String getUserInfo(String id) throws JsonProcessingException {
		Optional<User> user = findUserById(id);
		if (user.isPresent()) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new Jdk8Module());

			String serializedUser = mapper.writeValueAsString(user);

			return serializedUser;
		}
		return "User not exist.";
	}

	@Override
	public Optional<User> findUserById(String id) {
		Optional<User> user = userRepo.findById(id);
		return user;
	}

}
