package com.fmalessio.twitterapi.service;

import java.util.Optional;

import com.fmalessio.twitterapi.entity.User;

public interface UsersService {

	String getUserInfo(String id);

	Optional<User> findUserById(String id);

}
