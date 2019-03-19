package com.fmalessio.twitterapi.service;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fmalessio.twitterapi.entity.User;

public interface UsersService {

	String login(User user) throws JsonProcessingException;

	String getUserInfo(String id) throws JsonProcessingException;

	Optional<User> findUserById(String id);

}
